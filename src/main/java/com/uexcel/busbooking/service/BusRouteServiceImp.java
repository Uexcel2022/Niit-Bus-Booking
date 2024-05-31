package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusCheckinInfoDto;
import com.uexcel.busbooking.dto.BusCheckinQueryDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Checkin;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import com.uexcel.busbooking.utils.Validation;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class BusRouteServiceImp implements BusRouteService{

    private final Validation validation;
    private final Repos repos;
    private final String statusCode404 = "404";
    private final String  statusCode400 = "400";

    public BusRouteServiceImp(Validation validation, Repos repos) {

        this.validation = validation;
        this.repos = repos;
    }


    @Override
    public String updateBusRoute(String busCode, String routeName) {
        Bus bus = repos.getBusRepository().findByBusCode(busCode);
        if(bus == null) {
            throw new CustomException("Invalid bus code.",statusCode400);
        }
        Route route = repos.getRouteRepository().findByRouteName(routeName);
        if(route == null) {
            throw new CustomException("Invalid route name.",statusCode400);
        }

        bus.setRoute(route);
        repos.getBusRepository().save(bus);
        return "Bus route updated successfully.";

    }

    @Override
    public List<Bus> findAllBus() {
        return  repos.getBusRepository().findAll();
    }

    @Override
    public List<Route> findAllRoute() {
        return (List<Route>) repos.getRouteRepository().findAll();
    }

    @Override
    public Bus findBusByCode(String busCode) {

        Bus bus = repos.getBusRepository().findByBusCode(busCode);
        if(bus == null) {
            throw new CustomException("Invalid bus code.",statusCode400);
        }
        return bus;
    }

    @Override
    public Route findRoutByName(String routeName) {

        Route route = repos.getRouteRepository().findByRouteName(routeName);
        if(route == null) {
            throw new CustomException("Invalid route name.",statusCode400);
        }
        return route;
    }

    @Override
    public String updateRoute(String routeId, BusRouteDto busRouteDto) {
        Optional<Route> route = repos.getRouteRepository().findById(routeId);
        if(route.isPresent()) {
            Route routeToUpdate = route.get();
            routeToUpdate.setRouteName(busRouteDto.getRouteName());
            routeToUpdate.setPrice(route.get().getPrice());
            repos.getRouteRepository().save(routeToUpdate);
            return"Route updated successfully.";

        } else throw new CustomException("Invalid route.",statusCode400);
    }

    @Override
    public String updateBus(String busId, BusRouteDto busRouteDto) {
        Optional<Bus> bus = repos.getBusRepository().findById(busId);
        if(bus.isPresent()) {
            Bus busToUpdate = bus.get();

            if(validation.checkNullBlank(busRouteDto.getBusCode())) {
                busToUpdate.setBusCode(busRouteDto.getBusCode());
            }
            if(busRouteDto.getBusCapacity() != 0) {
                busToUpdate.setBusCapacity(busRouteDto.getBusCapacity());
            }
            if(validation.checkNullBlank(busRouteDto.getBrand())) {
                busToUpdate.setBrand(busRouteDto.getBrand());
            }
            if(busRouteDto.getServiceStartDate()!= null) {
                busToUpdate.setServiceEndDate(busRouteDto.getServiceEndDate());
            }
            if(busRouteDto.getServiceEndDate()!= null) {
                busToUpdate.setServiceStartDate(busRouteDto.getServiceStartDate());
            }

            repos.getBusRepository().save(busToUpdate);

            return "Bus updated successfully.";

        } else throw new CustomException("Invalid bus id.",statusCode400);
    }

    @Override
    public String addBus(BusRouteDto busRouteDto) {
        Route rt;
        Optional<Route> route = repos.getRouteRepository().findById(busRouteDto.getRouteId());
        if(route.isPresent()) {
            rt = route.get();
        }else {throw new CustomException("Invalid route.",statusCode400);}

        Bus bus = new Bus();
        bus.setBusCode(busRouteDto.getBusCode());
        bus.setBrand(busRouteDto.getBrand());
        bus.setModel(busRouteDto.getModel());
        bus.setBusCapacity(busRouteDto.getBusCapacity());
        bus.setServiceStartDate(busRouteDto.getServiceStartDate());
        bus.setRoute(rt);
        repos.getBusRepository().save(bus);
        return "Bus added successfully.";
    }

    public String addRout(BusRouteDto busRouteDto) {
        Route route = new Route();
        route.setRouteName(busRouteDto.getRouteName());
        route.setPrice(busRouteDto.getPrice());
        repos.getRouteRepository().save(route);
        return "Route added successfully.";
    }

    public List<BusCheckinInfoDto> findBusesOnRoute(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = repos.getCheckinRepository().findByBusCurrentRouteId(
                busCheckinQueryDto.getBusCurrentRouteId());
        if(checkin.isEmpty()){
            throw new CustomException("Route not found.",statusCode404);
        }

        return filterResultSet(checkin);
    }

    public List<BusCheckinInfoDto> findBusesOnRouteByDate(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = repos.getCheckinRepository().findByBusCurrentRouteIdAndCheckinDate(
                busCheckinQueryDto.getBusCurrentRouteId(), busCheckinQueryDto.getDate());
        if(checkin.isEmpty()){
            throw new CustomException("Not found.",statusCode404);
        }
        return filterResultSet(checkin);
    }

    public List<BusCheckinInfoDto> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = repos.getCheckinRepository().findByBusCode(busCheckinQueryDto.getBusCode());
        if(checkin.isEmpty()){
            throw new CustomException("Not found.", statusCode404);
        }
        return filterResultSet(checkin);
    }

    @Override
    public List<BusCheckinInfoDto> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = repos.getCheckinRepository().findByBusCodeAndCheckinDate(
                busCheckinQueryDto.getBusCode(),busCheckinQueryDto.getDate());
        if(checkin.isEmpty()){
            throw new CustomException("Not found.",statusCode404);
        }
        return filterResultSet(checkin);
    }



    private static   List<BusCheckinInfoDto> filterResultSet(List<Checkin> checkin){
        List<BusCheckinInfoDto> busCheckinInfo = new ArrayList<>();
        for(Checkin checkin1 : checkin){
            BusCheckinInfoDto busCheckinInfoDto = new BusCheckinInfoDto();
            busCheckinInfoDto.setBookingId(checkin1.getBooking().getId());
            busCheckinInfoDto.setBusCode(checkin1.getBusCode());
            busCheckinInfoDto.setRouteName(checkin1.getBooking().getRoute().getRouteName());
            busCheckinInfoDto.setCheckinDate(checkin1.getCheckinDate());
            busCheckinInfo.add(busCheckinInfoDto);
        }
        return busCheckinInfo;
    }


}
