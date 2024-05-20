package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;

import java.util.List;

public interface SignupService {
    User processSignup(RegistrationData registrationData);

    User getUser(QueryUser queryUser);

    User getUserById(Long id);

    List<User> findAllUsers();

    NextOfKin getNextOfKinById(Long id);

    NextOfKin updateNextOfKin(Long id, NextOfKin nextOfKin);

    User updateUser(Long id, User user);

    ResponseDto processWalletFunding(WalletFundingDto walletFundingDto);
}
