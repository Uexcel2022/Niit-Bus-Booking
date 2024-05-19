package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Booking;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/api/v1/add_bus")
    public ResponseEntity<Bus> addBus(@RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.ok().body(bookingService.addBus(busRouteDto));
    }

    @GetMapping("/api/v1/book/{userId}/{routeId}")
    public ResponseEntity<Booking> book(@PathVariable("userId") Long userId,
                                        @PathVariable("routeId") Long routeId,
                                        @RequestBody Booking booking){
        return ResponseEntity.ok().body(bookingService.processBooking(userId,routeId,booking));

    }
}
