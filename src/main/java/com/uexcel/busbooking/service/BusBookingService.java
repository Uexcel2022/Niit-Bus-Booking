package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.Signup;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface BusBookingService {
    Signup setSignup(RegistrationData registrationData);

    Signup getUser(QueryUser queryUser);
}
