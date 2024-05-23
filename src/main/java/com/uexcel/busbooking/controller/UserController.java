package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.NextOfKin;
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
        return ResponseEntity.ok(userService.getUserByEmail(useEmailPasswordDto));
    }

    @GetMapping("/api/v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/api/v1/all_users")
    public ResponseEntity< List<User>> getAllUser(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/api/v1/next_of_kin/{id}")
    public ResponseEntity<NextOfKin> viewNextOfKin(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.getNextOfKinById(id));
    }

    @PutMapping("/api/v1/update_next_of_kin/{id}")
    public ResponseEntity<NextOfKin> updateNextOfKinById(@PathVariable("id") Long id,
                                                                       @RequestBody NextOfKin nextOfKin){
        return ResponseEntity.ok().body(userService.updateNextOfKin(id, nextOfKin));
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
//    @GetMapping("/api/v1/user_wallet/{userId}")
//    public ResponseEntity<UserWallet> findWalletTransactionById(@PathVariable Long userId){
//       return ResponseEntity.ok().body(userService.findUserWallet(userId));
//
//    }

}
