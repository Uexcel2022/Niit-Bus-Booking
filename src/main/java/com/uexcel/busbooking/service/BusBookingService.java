package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.Signup;

import java.util.List;
import java.util.Optional;

public interface BusBookingService {
    Signup setSignup(RegistrationData registrationData);

    Signup getUser(QueryUser queryUser);

    Optional<Signup> getUserById(Long id);

    List<Signup> findAllUsers();
}
