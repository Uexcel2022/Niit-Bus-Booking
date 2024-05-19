package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    SignupService signupService;
    public UserController(SignupService signupService){
        this.signupService = signupService;
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<User> signup(@RequestBody RegistrationData registrationData) {
      return  ResponseEntity.ok().body(signupService.processSignup(registrationData));
    }

    @PostMapping("/api/v1/fetch_user_email")
    public ResponseEntity<User> getUserByEmail(@RequestBody QueryUser queryUser){
        return ResponseEntity.ok(signupService.getUser(queryUser));
    }

    @GetMapping("/api/v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(signupService.getUserById(id));
    }

    @GetMapping("/api/v1/all_users")
    public ResponseEntity< List<User>> getAllUser(){
        return ResponseEntity.ok(signupService.findAllUsers());
    }

    @GetMapping("/api/v1/next_of_kin/{id}")
    public ResponseEntity<NextOfKin> viewNextOfKin(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(signupService.getNextOfKinById(id));
    }

    @PostMapping("/api/v1/update_next_of_kin/{id}")
    public ResponseEntity<NextOfKin> updateNextOfKinById(@PathVariable("id") Long id,
                                                                       @RequestBody NextOfKin nextOfKin){
        return ResponseEntity.ok().body(signupService.updateNextOfKin(id, nextOfKin));
    }

    @PostMapping("/api/v1/update_user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return ResponseEntity.ok().body(signupService.updateUser(id, user));
    }



}
