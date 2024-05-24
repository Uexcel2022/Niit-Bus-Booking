package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.entity.UserWallet;
import com.uexcel.busbooking.validation.Validation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SignupServiceImp implements SignupService{
    private final UserService userService;
    private final UserWalletService userWalletService;
    private final NextOfKinService nextOfKinService;
      public SignupServiceImp(UserService userService,
                              UserWalletService userWalletService,
              NextOfKinService nextOfKinService){
          this.userService = userService;
          this.userWalletService = userWalletService;
          this.nextOfKinService = nextOfKinService;
      }

    public ResponseDto processSignup(RegistrationData registrationData) {

          User isExist = userService.getUserRepository()
                  .findByEmailOrPhoneNumber(registrationData.getEmail(),registrationData.getPhoneNumber());
          if(isExist != null){
              throw new RuntimeException("User already exists. Use another email or phone number to register.");
          }


        User user = new User();
        NextOfKin nextOfKin = new NextOfKin();
        UserWallet userWallet = new UserWallet();

        if(!Validation.checkName(registrationData.getFirstName())){
            throw new RuntimeException("Name is not valid.");
        }
        if(!Validation.checkName(registrationData.getNFirstName())){
            throw new RuntimeException("Name is not valid.");
        }
        if(!Validation.checkName(registrationData.getLastName())){
            throw new RuntimeException("Name is not valid.");
        }
        if(!Validation.checkName(registrationData.getNLastName())){
            throw new RuntimeException("Name is not valid.");
        }
        if(!Validation.checkEmail(registrationData.getEmail())){
            throw new RuntimeException("Email is not valid.");
        }
         if(Validation.checkPhone(registrationData.getPhoneNumber())){
             throw new RuntimeException("User phone number is not valid.");
         }

        if(Validation.checkPhone(registrationData.getNPhoneNumber())){
            throw new RuntimeException("Next of kin phone number is not valid.");
        }

        if(Validation.checkNullBlank(registrationData.getAddress())){
            throw new RuntimeException("Address is required.");
        }

        if(Validation.checkNullBlank(registrationData.getLga())){
            throw new RuntimeException("Local Government Area is required.");

        }

        if(Validation.checkNullBlank(registrationData.getState())){
            throw new RuntimeException("State is required.");
        }

        if(Validation.checkNullBlank(registrationData.getBirthDate())){
            throw new RuntimeException("Birth date is required.");
        }

        if(Validation.checkPassword(registrationData.getPassword())){
            throw new RuntimeException("Password must be >= 6 character and must contain at least one uppercase," +
                    " number and special character; not less than 6 and not more than 8 characters");
        }


        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setBirthDate(LocalDate.parse(registrationData.getBirthDate()));
        user.setEmail(registrationData.getEmail());
        user.setPassword(registrationData.getPassword());
        user.setPhoneNumber(registrationData.getPhoneNumber());

        nextOfKin.setNFirstName(registrationData.getNFirstName());
        nextOfKin.setNLastName(registrationData.getNLastName());
        nextOfKin.setAddress(registrationData.getAddress());
        nextOfKin.setLga(registrationData.getLga());
        nextOfKin.setState(registrationData.getState());
        nextOfKin.setNPhoneNumber(registrationData.getNPhoneNumber());

        userWallet.setBalance(0.0);
        userWallet.setStatus("active");

        userService.getUserRepository().save(user);
        nextOfKin.setUser(user);
        userWallet.setUser(user);
        userWalletService.getUserWalletRepository().save(userWallet);
        nextOfKinService.getNextOfKinRepository().save(nextOfKin);
        ResponseDto responseDto = new ResponseDto();
         responseDto.setResponse("You have successfully registered!");
        return responseDto;
    }

}
