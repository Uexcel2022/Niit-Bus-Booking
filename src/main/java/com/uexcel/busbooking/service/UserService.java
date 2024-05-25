package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.UserRepository;

import java.util.List;

public interface UserService {
    UserRepository getUserRepository();

    User findByUserByEmail(UseEmailPasswordDto useEmailPasswordDto);

    User findByUserById(String id);

    List<User> findAllUsers();

    User updateUser(String id, User user);

}
