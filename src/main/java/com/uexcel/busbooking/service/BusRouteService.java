package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.repository.RouteRepository;

import java.util.List;

public interface BusRouteService {
    Bus addBus(BusRouteDto busRouteDto);
    public RouteRepository getRouteRepository();
    public BusRepository getBusRepository();

    ResponseDto updateBusRoute(String busCode, String routeName);

    List<Bus> findAllBus();

    List<Route> findAllRoute();

    Bus findBusByCode(String busCode);

    Route findRoutByName(String routeName);

    ResponseDto updateRoute(Long routeId, BusRouteDto busRouteDto);

    ResponseDto updateBus(Long busId, BusRouteDto busRouteDto);
}
