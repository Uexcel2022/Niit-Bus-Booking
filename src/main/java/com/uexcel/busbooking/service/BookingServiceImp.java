package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingService {
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    public BookingServiceImp(BusRepository busRepository,
                             RouteRepository routeRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
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