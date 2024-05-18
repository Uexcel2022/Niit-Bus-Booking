package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.Signup;

import java.util.List;

public interface BusBookingService {
    Signup setSignup(RegistrationData registrationData);

    Signup getUser(QueryUser queryUser);

    Signup getUserById(Long id);

    List<Signup> findAllUsers();

    NextOfKin getNextOfKinById(Long id);

    NextOfKin updateNextOfKin(Long id, NextOfKin nextOfKin);

    Signup updateUser(Long id, Signup signup);
}
