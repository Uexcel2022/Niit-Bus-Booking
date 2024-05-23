package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.UserRepository;

import java.util.List;

public interface UserService {
    UserRepository getUserRepository();

    User getUserByEmail(UseEmailPasswordDto useEmailPasswordDto);

    User getUserById(Long id);

    List<User> findAllUsers();

    NextOfKin getNextOfKinById(Long id);

    NextOfKin updateNextOfKin(Long id, NextOfKin nextOfKin);

    User updateUser(Long id, User user);


//    ResponseDto deleteUser(Long userId);

//    UserWallet findUserWallet(Long userId);

//    NextOfKin findNextOfKinByUsrId(Long id);
}
