package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.Signup;
import com.uexcel.busbooking.service.BusBookingService;
import com.uexcel.busbooking.service.BusBookingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusBookingController {
    BusBookingService busBookingService;
    public BusBookingController(){
        busBookingService = new BusBookingServiceImp();
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<Signup> signup(@RequestBody RegistrationData registrationData) {
      return  ResponseEntity.ok().body(busBookingService.setSignup(registrationData));
    }
}
