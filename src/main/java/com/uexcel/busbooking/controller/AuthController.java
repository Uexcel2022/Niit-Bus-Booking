package com.uexcel.busbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    final String name = "";

    @PostMapping("login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok().body("walletService: login");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestHeader(name) String auth){
        return ResponseEntity.ok().body("walletService: register");
    }

}
