package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.UserRepository;

import java.util.List;

public interface UserService {
    UserRepository getUserRepository();

    User findByUserByEmail(UseEmailPasswordDto useEmailPasswordDto);

    User findByUserById(Long id);

    List<User> findAllUsers();

    User updateUser(Long id, User user);

}
