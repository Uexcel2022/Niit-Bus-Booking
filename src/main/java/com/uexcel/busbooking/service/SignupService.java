package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.SignupDto;
import com.uexcel.busbooking.dto.ResponseDto;

public interface SignupService {
    ResponseDto processSignup(SignupDto signupDto);
}
