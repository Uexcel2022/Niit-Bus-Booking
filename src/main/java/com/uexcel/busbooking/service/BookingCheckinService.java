package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.*;
import com.uexcel.busbooking.entity.Checkin;

import java.util.List;

public interface BookingCheckinService {

//    Bus addBus(BusRouteDto busRouteDto);

    BookingInfoDto processBooking(String userId, String routeId);

    ResponseDto processCheckin(CheckinDto checkinDto);

    public List<Checkin> findBusesOnRouteByDate(BusCheckinQueryDto bookingQueryDto);

    public List<Checkin> findBusesOnRoute(BusCheckinQueryDto bookingQueryDto);

    public List<Checkin> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto);

    List<Checkin> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto);
}
