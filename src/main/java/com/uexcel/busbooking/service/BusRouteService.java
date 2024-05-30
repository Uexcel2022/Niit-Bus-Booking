package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusCheckinInfoDto;
import com.uexcel.busbooking.dto.BusCheckinQueryDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;

import java.util.List;

public interface BusRouteService {
    String addRout(BusRouteDto busRouteDto);

    String addBus(BusRouteDto busRouteDto);

//    RouteRepository getRouteRepository();
//    BusRepository getBusRepository();

    String updateBusRoute(String busCode, String routeName);

    List<Bus> findAllBus();

    List<Route> findAllRoute();

    Bus findBusByCode(String busCode);

    Route findRoutByName(String routeName);

    String updateRoute(String routeId, BusRouteDto busRouteDto);

    String updateBus(String busId, BusRouteDto busRouteDto);



    public List<BusCheckinInfoDto> findBusesOnRouteByDate(BusCheckinQueryDto bookingQueryDto);

    public List<BusCheckinInfoDto> findBusesOnRoute(BusCheckinQueryDto bookingQueryDto);

    public List<BusCheckinInfoDto> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto);

    List<BusCheckinInfoDto> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto);
}
