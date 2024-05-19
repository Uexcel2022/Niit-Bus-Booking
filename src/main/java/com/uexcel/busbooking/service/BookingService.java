package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Booking;
import com.uexcel.busbooking.entity.Bus;

public interface BookingService {
    Bus addBus(BusRouteDto busRouteDto);

    Booking processBooking(Long userId, Long routeId, Booking booking);
}
