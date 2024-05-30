package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.*;

import java.util.List;

public interface BookingCheckinService {

    BookingInfoDto processBooking(String userId, String routeId);

    String processCheckin(CheckinDto checkinDto);

    List<BookingInfoDto> findBookingByClientId(String clientId);

    List<BookingInfoDto> findByClientIdAndTicketStatus(String clientId, String status);

    List<BookingInfoDto> findAllTicketByStatusAndRouteName(SearchingTicketDto searchingTicketDto);

    List<BookingInfoDto> findAllTicketByStatusAndRouteNameAndPhone(SearchingTicketDto searchingTicketDto);

}
