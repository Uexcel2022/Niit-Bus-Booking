package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusCheckinInfoDto;
import com.uexcel.busbooking.dto.BusCheckinQueryDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.repository.RouteRepository;

import java.util.List;

public interface BusRouteService {
    ResponseDto addRout(BusRouteDto busRouteDto);

    ResponseDto addBus(BusRouteDto busRouteDto);

//    RouteRepository getRouteRepository();
//    BusRepository getBusRepository();

    ResponseDto updateBusRoute(String busCode, String routeName);

    List<Bus> findAllBus();

    List<Route> findAllRoute();

    Bus findBusByCode(String busCode);

    Route findRoutByName(String routeName);

    ResponseDto updateRoute(String routeId, BusRouteDto busRouteDto);

    ResponseDto updateBus(String busId, BusRouteDto busRouteDto);



    public List<BusCheckinInfoDto> findBusesOnRouteByDate(BusCheckinQueryDto bookingQueryDto);

    public List<BusCheckinInfoDto> findBusesOnRoute(BusCheckinQueryDto bookingQueryDto);

    public List<BusCheckinInfoDto> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto);

    List<BusCheckinInfoDto> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto);
}
