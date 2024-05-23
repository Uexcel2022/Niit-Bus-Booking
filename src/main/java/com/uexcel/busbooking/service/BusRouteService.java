package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.repository.RouteRepository;

public interface BusRouteService {
    Bus addBus(BusRouteDto busRouteDto);
    public RouteRepository getRouteRepository();
    public BusRepository getBusRepository();
}
