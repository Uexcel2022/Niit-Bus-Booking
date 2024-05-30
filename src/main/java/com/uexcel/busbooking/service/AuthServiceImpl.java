package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.LoginDto;
import com.uexcel.busbooking.dto.SignupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<String> login(LoginDto loginDto) {
        return null;
    }

    @Override
    public ResponseEntity<String> signUp(SignupDto signupDto) {
        return null;
    }
}
