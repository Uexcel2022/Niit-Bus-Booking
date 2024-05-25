package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.entity.UserWallet;
import com.uexcel.busbooking.exception.BadRequestException;
import com.uexcel.busbooking.utils.Validation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SignupServiceImp implements SignupService{
    private final UserService userService;
    private final UserWalletService userWalletService;
    private final NextOfKinService nextOfKinService;
    private final Validation validation;
      public SignupServiceImp(UserService userService,
                              UserWalletService userWalletService,
                              NextOfKinService nextOfKinService, Validation validation){
          this.userService = userService;
          this.userWalletService = userWalletService;
          this.nextOfKinService = nextOfKinService;
          this.validation = validation;
      }

    public ResponseDto processSignup(RegistrationData registrationData) {

          User isExist = userService.getUserRepository()
                  .findByEmailOrPhoneNumber(registrationData.getEmail(),registrationData.getPhoneNumber());
          if(isExist != null){
              throw new BadRequestException("This email or phone number has already been used.");
          }

//          boolean isExistsIgnoreCase = userService.getUserRepository()
//                  .existsByEmailIgnoreCase(registrationData.getEmail());


        User user = new User();
        NextOfKin nextOfKin = new NextOfKin();
        UserWallet userWallet = new UserWallet();

        if(validation.checkName(registrationData.getFirstName())){
            throw new BadRequestException("Name is not valid.");
        }
        if(validation.checkName(registrationData.getNFirstName())){
            throw new BadRequestException("Name is not valid.");
        }
        if(validation.checkName(registrationData.getLastName())){
            throw new BadRequestException("Name is not valid.");
        }
        if(validation.checkName(registrationData.getNLastName())){
            throw new BadRequestException("Name is not valid.");
        }
        if(validation.checkEmail(registrationData.getEmail())){
            throw new BadRequestException("Email is not valid.");
        }
         if(validation.checkPhone(registrationData.getPhoneNumber())){
             throw new BadRequestException("User phone number is not valid.");
         }

        if(validation.checkPhone(registrationData.getNPhoneNumber())){
            throw new BadRequestException("Next of kin phone number is not valid.");
        }

        if(validation.checkNullBlank(registrationData.getAddress())){
            throw new BadRequestException("Address is required.");
        }

        if(validation.checkNullBlank(registrationData.getLga())){
            throw new BadRequestException("Local Government Area is required.");

        }

        if(validation.checkNullBlank(registrationData.getState())){
            throw new BadRequestException("State is required.");
        }

        if(!Validation.checkDaDate(registrationData.getBirthDate())){
            if(!LocalDate.parse(registrationData.getBirthDate()).isBefore(LocalDate.now())){
                throw new BadRequestException("Birth date is not valid.");
            }

        } else {
            throw new BadRequestException("Birth date is invalid.");
        }

        if(validation.checkPassword(registrationData.getPassword())){
            throw new BadRequestException("Password must be >= 6 character and must contain at least one uppercase," +
                    " number and special character; not less than 6 and not more than 16 characters");
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
