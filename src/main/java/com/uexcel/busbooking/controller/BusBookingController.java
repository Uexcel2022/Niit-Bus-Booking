package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.Signup;
import com.uexcel.busbooking.service.BusBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BusBookingController {
    BusBookingService busBookingService;
    public BusBookingController(BusBookingService busBookingService){
        this.busBookingService = busBookingService;
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<Signup> signup(@RequestBody RegistrationData registrationData) {
      return  ResponseEntity.ok().body(busBookingService.setSignup(registrationData));
    }

    @PostMapping("/api/v1/fetch_user_email")
    public ResponseEntity<Signup> getUserByEmail(@RequestBody QueryUser queryUser){
        return ResponseEntity.ok(busBookingService.getUser(queryUser));
    }

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<Optional<Signup>> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(busBookingService.getUserById(id));
    }

    @GetMapping("/api/v1/all_users")
    public ResponseEntity< List<Signup>> getAllUser(){
        return ResponseEntity.ok(busBookingService.findAllUsers());
    }
}
