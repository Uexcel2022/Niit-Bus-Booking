package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.entity.UserWallet;
import org.springframework.stereotype.Service;

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

        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setBirthDate(registrationData.getBirthDate());
        user.setEmail(registrationData.getEmail());
        user.setPassword(registrationData.getPassword());
        user.setPhoneNumber(registrationData.getPhoneNumber());

        nextOfKin.setNFirstName(registrationData.getNFirstName());
        nextOfKin.setNLastName(registrationData.getNLastName());
        nextOfKin.setAddress(registrationData.getAddress());
        nextOfKin.setLga(registrationData.getLga());
        nextOfKin.setStreet(registrationData.getStreet());
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
