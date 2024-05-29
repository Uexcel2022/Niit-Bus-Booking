package com.uexcel.busbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    final String name = "";

    @GetMapping("test")
    public ResponseEntity<String> fundWallet(@RequestHeader(name) String auth){
        return ResponseEntity.ok().body("walletService.processWalletFunding(walletFundingDto)");
    }

}
