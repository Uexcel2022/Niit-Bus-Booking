package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.SignupDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import com.uexcel.busbooking.utils.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SignupServiceImp implements SignupService{
    private final Repos repos;
    private final Validation validation;
      public SignupServiceImp(Validation validation, Repos repos){
          this.validation = validation;
          this.repos = repos;
      }

      @Transactional
    public ResponseDto processSignup(SignupDto signupDto) {

          if(repos.getClientRepository()
                  .findByEmail(signupDto.getEmail()) != null){
              throw new CustomException("The email is in use.","400");
          }

        if(repos.getClientRepository()
                .findByPhoneNumber(signupDto.getPhoneNumber()) != null){
            throw new CustomException("The phone number is in use.","400");
        }

//        if(repos.getClientWalletRepository()
//                .findByWalletNumber(signupDto.getWalletNumber()) != null){
//            throw new CustomException("Wallet number is in use.","400");
//        }

        Client client = new Client();
        NextOfKin nextOfKin = new NextOfKin();
        ClientWallet clientWallet = new ClientWallet();

        if(validation.checkName(signupDto.getFullName())){
            throw new CustomException("First name is not valid.","400");
        }
        if(validation.checkName(signupDto.getNFullName())){
            throw new CustomException("Next of kin first name is not valid.","400");
        }
//        if(validation.checkNullBlank(signupDto.getGender())){
//            throw new CustomException("Client gender field can not be empty.","400");
//        }
//        if(validation.checkNullBlank(signupDto.getNGender())){
//            throw new CustomException("Next of kin gender field can not be empty.","400");
//        }
        if(validation.checkEmail(signupDto.getEmail())){
            throw new CustomException("Email is not valid.","400");
        }
         if(validation.checkPhone(signupDto.getPhoneNumber())){
             throw new CustomException("User phone number is not valid.","400");
         }

        if(validation.checkPhone(signupDto.getNPhoneNumber())){
            throw new CustomException("Next of kin phone number is not valid.","400");
        }

        if(validation.checkNullBlank(signupDto.getAddress())){
            throw new CustomException("Address is required.","400");
        }

        if(validation.checkNullBlank(signupDto.getLga())){
            throw new CustomException("Local Government Area is required.","400");

        }

        if(validation.checkNullBlank(signupDto.getState())){
            throw new CustomException("State is required.","400");
        }

        if(validation.checkAccountNumber(signupDto.getWalletNumber())){
            throw new CustomException("The account number is not valid.","400");
        }

        if(!Validation.checkDaDate(signupDto.getBirthDate())){
            if(!LocalDate.parse(signupDto.getBirthDate()).isBefore(LocalDate.now())){
                throw new CustomException("Birth date is not valid.","400");
            }

        } else {
            throw new CustomException("Birth date is invalid.","400");
        }

        if(validation.checkPassword(signupDto.getPassword())){
            throw new CustomException("Password must be >= 6 character and must contain at least one uppercase," +
                    " number and special character; not less than 6 and not more than 16 characters","400");
        }


        client.setFullName(signupDto.getFullName());
//        client.setGender(signupDto.getGender());
        client.setBirthDate(LocalDate.parse(signupDto.getBirthDate()));
        client.setEmail(signupDto.getEmail());
        client.setPassword(signupDto.getPassword());
        client.setPhoneNumber(signupDto.getPhoneNumber());

        nextOfKin.setNFullName(signupDto.getNFullName());
//        nextOfKin.setNGender(signupDto.getNGender());
        nextOfKin.setAddress(signupDto.getAddress());
        nextOfKin.setLga(signupDto.getLga());
        nextOfKin.setState(signupDto.getState());
        nextOfKin.setNPhoneNumber(signupDto.getNPhoneNumber());

        clientWallet.setBalance(0.0);
        clientWallet.setStatus("active");
        clientWallet.setWalletNumber(signupDto.getWalletNumber());

        repos.getClientRepository().save(client);
        nextOfKin.setClient(client);
        clientWallet.setClient(client);
        repos.getClientWalletRepository().save(clientWallet);
        repos.getNextOfKinRepository().save(nextOfKin);
        ResponseDto responseDto = new ResponseDto();
         responseDto.setResponse("You have successfully registered!");
        return responseDto;
    }

}
