package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.*;

import java.util.List;

public interface BookingCheckinService {

//    Bus addBus(BusRouteDto busRouteDto);

    BookingInfoDto processBooking(String userId, String routeId);

    ResponseDto processCheckin(CheckinDto checkinDto);

//    public List<BusCheckinInfoDto> findBusesOnRouteByDate(BusCheckinQueryDto bookingQueryDto);
//
//    public List<BusCheckinInfoDto> findBusesOnRoute(BusCheckinQueryDto bookingQueryDto);
//
//    public List<BusCheckinInfoDto> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto);
//
//    List<BusCheckinInfoDto> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto);

    public List<BookingInfoDto> findBookingByClientId(String clientId);

    List<BookingInfoDto> findByClientIdAndTicketStatus(String clientId, String status);

}
