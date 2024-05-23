package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/v1/fetch_user_email")
    public ResponseEntity<User> getUserByEmail(@RequestBody UseEmailPasswordDto useEmailPasswordDto){
        return ResponseEntity.ok(userService.findByUserByEmail(useEmailPasswordDto));
    }

    @GetMapping("/api/v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findByUserById(id));
    }

    @GetMapping("/api/v1/all_users")
    public ResponseEntity< List<User>> getAllUser(){
        return ResponseEntity.ok(userService.findAllUsers());
    }


    @PutMapping("/api/v1/update_user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }

//    @DeleteMapping("/api/v1/delete_user/{userId}")
//    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long userId){
//        return ResponseEntity.ok().body(signupService.deleteUser(userId));
//
//    }

}
