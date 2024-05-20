package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BookingInfoDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.CheckinDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Booking;
import com.uexcel.busbooking.entity.Bus;

public interface BookingService {
    Bus addBus(BusRouteDto busRouteDto);

    BookingInfoDto processBooking(Long userId, Long routeId)
            ;

    ResponseDto processCheckin(CheckinDto checkinDto);
}
