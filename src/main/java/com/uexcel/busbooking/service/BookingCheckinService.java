package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.*;

import java.util.List;

public interface BookingCheckinService {

    BookingInfoDto processBooking(String userId, String routeId);

    ResponseDto processCheckin(CheckinDto checkinDto);

    public List<BookingInfoDto> findBookingByClientId(String clientId);

    List<BookingInfoDto> findByClientIdAndTicketStatus(String clientId, String status);

    List<BookingInfoDto> findAllTicketByStatusAndRouteName(SearchingTicketDto searchingTicketDto);

    public List<BookingInfoDto> findAllTicketByStatusAndRouteNameAndPhone(SearchingTicketDto searchingTicketDto);

}
