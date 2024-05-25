package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.utils.Validation;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class BusRouteServiceImp implements BusRouteService{
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final Validation validation;

    public BusRouteServiceImp(RouteRepository routeRepository,
                              BusRepository busRepository, Validation validation) {
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
        this.validation = validation;
    }

    @Override
    public RouteRepository getRouteRepository() {
        return routeRepository;
    }

    @Override
    public BusRepository getBusRepository() {
        return busRepository;
    }

    @Override
    public ResponseDto updateBusRoute(String busCode, String routeName) {
        Bus bus = busRepository.findByBusCode(busCode);
        if(bus == null) {
            throw new RuntimeException("Invalid bus code");
        }
        Route route = routeRepository.findByRouteName(routeName);
        if(route == null) {
            throw new RuntimeException("Invalid route name");
        }

        bus.setRoute(route);
        busRepository.save(bus);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Bus route updated successfully");
        return responseDto;
    }

    @Override
    public List<Bus> findAllBus() {
        return  busRepository.findAll();
    }

    @Override
    public List<Route> findAllRoute() {
        return (List<Route>) routeRepository.findAll();
    }

    @Override
    public Bus findBusByCode(String busCode) {
        return busRepository.findByBusCode(busCode);
    }

    @Override
    public Route findRoutByName(String routeName) {
        return routeRepository.findByRouteName(routeName);
    }

    @Override
    public ResponseDto updateRoute(String routeId, BusRouteDto busRouteDto) {
        Optional<Route> route = routeRepository.findById(routeId);
        if(route.isPresent()) {
            Route routeToUpdate = route.get();
            routeToUpdate.setRouteName(busRouteDto.getRouteName());
            routeToUpdate.setPrice(route.get().getPrice());
            routeRepository.save(routeToUpdate);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResponse("Route updated successfully");
            return responseDto;
        } else throw new RuntimeException("Invalid route id");
    }

    @Override
    public ResponseDto updateBus(String busId, BusRouteDto busRouteDto) {
        Optional<Bus> bus = busRepository.findById(busId);
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

            busRepository.save(busToUpdate);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResponse("Bus updated successfully");
            return responseDto;
        } else throw new RuntimeException("Invalid bus id");
    }

    @Override
    public Bus addBus(BusRouteDto busRouteDto) {
        Bus bus = new Bus();
        Route route = new Route();
        bus.setBusCode(busRouteDto.getBusCode());
        bus.setBrand(busRouteDto.getBrand());
        bus.setModel(busRouteDto.getModel());
        bus.setBusCapacity(busRouteDto.getBusCapacity());
        bus.setServiceStartDate(busRouteDto.getServiceStartDate());
        route.setRouteName(busRouteDto.getRouteName());
        route.setPrice(busRouteDto.getPrice());
        bus.setRoute(route);
        routeRepository.save(route);
        return busRepository.save(bus);
    }

}
