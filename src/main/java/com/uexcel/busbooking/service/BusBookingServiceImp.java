package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.Signup;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import com.uexcel.busbooking.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusBookingServiceImp implements BusBookingService {
   @Autowired
   private  SignupRepository signupRepository;
    @Autowired
    NextOfKinRepository nextOfKinRepository;
    @Override
    public Signup setSignup(RegistrationData registrationData) {
        Signup signup = new Signup();
        NextOfKin nextOfKin = new NextOfKin();
        signup.setFirstName(registrationData.getFirstName());
        signup.setLastName(registrationData.getLastName());
        signup.setEmail(registrationData.getEmail());
        signup.setPassword(registrationData.getPassword());
        signup.setPhoneNumber(registrationData.getPhoneNumber());
        nextOfKin.setNFirstName(registrationData.getNFirstName());
        nextOfKin.setNLastName(registrationData.getNLastName());
        nextOfKin.setAddress(registrationData.getAddress());
        nextOfKin.setLga(registrationData.getLga());
        nextOfKin.setStreet(registrationData.getStreet());
        nextOfKin.setState(registrationData.getState());
        nextOfKin.setNPhoneNumber(registrationData.getNPhoneNumber());
        signup.setNextOfKin(nextOfKin);
       return signupRepository.save(signup);
    }
}
