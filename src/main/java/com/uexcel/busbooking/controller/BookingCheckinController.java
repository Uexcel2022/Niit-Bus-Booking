package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.CheckinDto;
import com.uexcel.busbooking.dto.BookingInfoDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.service.BookingCheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingCheckinController {
    private final BookingCheckinService bookingCheckinService;
    public BookingCheckinController(BookingCheckinService bookingCheckinService){
        this.bookingCheckinService = bookingCheckinService;
    }


    @GetMapping("/api/v1/booking/{userId}/{routeId}")
    public ResponseEntity<BookingInfoDto> book(@PathVariable("userId") Long userId,
                                               @PathVariable("routeId") Long routeId){
        return ResponseEntity.ok().body(bookingCheckinService.processBooking(userId,routeId));

    }

    @PostMapping("/api/v1/checkin")
    public ResponseEntity<ResponseDto> booking(@RequestBody CheckinDto checkinDto){
        return  ResponseEntity.ok().body(bookingCheckinService.processCheckin(checkinDto));
    }
}
