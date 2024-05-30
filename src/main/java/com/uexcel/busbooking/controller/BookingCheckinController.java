package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.*;
import com.uexcel.busbooking.service.BookingCheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class BookingCheckinController {
    private final BookingCheckinService bookingCheckinService;
    public BookingCheckinController(BookingCheckinService bookingCheckinService){
        this.bookingCheckinService = bookingCheckinService;
    }


    @GetMapping("booking/{userId}/{routeId}")
    public ResponseEntity<BookingInfoDto> book(@PathVariable("userId") String userId,
                                               @PathVariable("routeId") String routeId){
        return ResponseEntity.ok().body(bookingCheckinService.processBooking(userId,routeId));

    }

    @PostMapping("checkin")
    public ResponseEntity<String> processCheckin(@RequestBody CheckinDto checkinDto){
        return  ResponseEntity.ok().body(bookingCheckinService.processCheckin(checkinDto));
    }

    @GetMapping("find-booking-by-clientId/{clientId}")
    public ResponseEntity<List<BookingInfoDto>> findBookingByClientId(@PathVariable String clientId){
        return ResponseEntity.ok().body(bookingCheckinService.findBookingByClientId(clientId));
    }

    @GetMapping("find-booking-by-clientId-status/{clientId}/{status}")
    public ResponseEntity<List<BookingInfoDto>> findBookingByClientIdAndStatus(@PathVariable String clientId,
                                                                               @PathVariable String status){
        return ResponseEntity.ok().body(bookingCheckinService.findByClientIdAndTicketStatus(clientId,status));
    }

    @PostMapping("find-booking-by-status-route-name")
    public ResponseEntity<List<BookingInfoDto>> findAllTicketByStatusAndRouteName(@RequestBody SearchingTicketDto searchingTicketDto){
        return ResponseEntity.ok().body(bookingCheckinService.findAllTicketByStatusAndRouteName(searchingTicketDto));
    }

    @PostMapping("find-booking-by-status-route-name-phone")
    public ResponseEntity<List<BookingInfoDto>> findAllTicketByStatusAndRouteNameAndPhone(@RequestBody SearchingTicketDto searchingTicketDto){
        return ResponseEntity.ok().body(bookingCheckinService.findAllTicketByStatusAndRouteNameAndPhone(searchingTicketDto));
    }
}
