package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    private final SignupService signupService;
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody RegistrationData registrationData) {
        return  ResponseEntity.status(201).body(signupService.processSignup(registrationData));
    }

}
