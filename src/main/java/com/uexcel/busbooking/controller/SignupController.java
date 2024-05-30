package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.SignupDto;
import com.uexcel.busbooking.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SignupController {

    private final SignupService signupService;
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
        return  ResponseEntity.status(201).body(signupService.processSignup(signupDto));
    }

}
