package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.LoginDto;
import com.uexcel.busbooking.dto.SignupDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> login(LoginDto loginDto);
    ResponseEntity<String> signUp(SignupDto signupDto);
}
