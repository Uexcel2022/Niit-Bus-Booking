package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.*;
import com.uexcel.busbooking.service.BookingCheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingCheckinController {
    private final BookingCheckinService bookingCheckinService;
    public BookingCheckinController(BookingCheckinService bookingCheckinService){
        this.bookingCheckinService = bookingCheckinService;
    }


    @GetMapping("/api/v1/booking/{userId}/{routeId}")
    public ResponseEntity<BookingInfoDto> book(@PathVariable("userId") String userId,
                                               @PathVariable("routeId") String routeId){
        return ResponseEntity.ok().body(bookingCheckinService.processBooking(userId,routeId));

    }

    @PostMapping("/api/v1/checkin")
    public ResponseEntity<ResponseDto> processCheckin(@RequestBody CheckinDto checkinDto){
        return  ResponseEntity.ok().body(bookingCheckinService.processCheckin(checkinDto));
    }

//    @PostMapping("/api/v1/find-buses-on-route-by-date")
//    public ResponseEntity<List<BusCheckinInfoDto>> findBusesOnRouteByDate(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
//       return ResponseEntity.ok().body(bookingCheckinService.findBusesOnRouteByDate(busCheckinQueryDto));
//    }
//
//    @PostMapping("/api/v1/find-buses-on-route")
//    public ResponseEntity<List<BusCheckinInfoDto>> findBusesOnRoute(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
//        return ResponseEntity.ok().body(bookingCheckinService.findBusesOnRoute(busCheckinQueryDto));
//    }
//
//    @PostMapping("/api/v1/find-bus-routes")
//    public ResponseEntity<List<BusCheckinInfoDto>> findBusRoutes(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
//        return ResponseEntity.ok().body(bookingCheckinService.findBusRoutes(busCheckinQueryDto));
//    }
//
//    @PostMapping("/api/v1/find-bus-routes-day")
//    public ResponseEntity<List<BusCheckinInfoDto>> findBusRoutesByDay(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
//        return ResponseEntity.ok().body(bookingCheckinService.findBusRoutesByDay(busCheckinQueryDto));
//    }

    @GetMapping("/api/v1/find-booking-by-clientId/{clientId}")
    public ResponseEntity<List<BookingInfoDto>> findBookingByClientId(@PathVariable String clientId){
        return ResponseEntity.ok().body(bookingCheckinService.findBookingByClientId(clientId));
    }

    @GetMapping("/api/v1/find-booking-by-clientId-status/{clientId}/{status}")
    public ResponseEntity<List<BookingInfoDto>> findBookingByClientIdAndStatus(@PathVariable String clientId,
                                                                               @PathVariable String status){
        return ResponseEntity.ok().body(bookingCheckinService.findByClientIdAndTicketStatus(clientId,status));
    }
}
