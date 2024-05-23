package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;

public interface SignupService {
    ResponseDto processSignup(RegistrationData registrationData);
}
