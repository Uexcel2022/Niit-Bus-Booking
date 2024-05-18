package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.Signup;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import com.uexcel.busbooking.repository.SignupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BusBookingServiceImp implements BusBookingService {

   private final   SignupRepository signupRepository;
    private final NextOfKinRepository nextOfKinRepository;

    public BusBookingServiceImp(SignupRepository signupRepository,
                                NextOfKinRepository nextOfKinRepository) {
        this.signupRepository = signupRepository;
        this.nextOfKinRepository = nextOfKinRepository;
    }
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
        nextOfKinRepository.save(nextOfKin);
       return signupRepository.save(signup);
    }

    @Override
    public Signup getUser(QueryUser queryUser) {
        return signupRepository.findByEmail(queryUser.getEmail());
    }

    @Override
    public Signup getUserById(Long id) {
        Optional<Signup> signup = signupRepository.findById(id);
        if (signup.isPresent()) {
            return signup.get();
        }else  throw new NoSuchElementException("User not found");
    }

    @Override
    public List<Signup> findAllUsers() {
        return signupRepository.findAll();
    }

    @Override
    public NextOfKin getNextOfKinById(Long id) {
        Optional<NextOfKin> nextOfKin = nextOfKinRepository.findById(id);
        if (nextOfKin.isPresent()) {
            return nextOfKin.get();
        }else  throw new NoSuchElementException("next of kin not found");
    }

    @Override
    public Optional<NextOfKin> updateNextOfKin(Long id,NextOfKin oldNextOfKin) {

        Optional<NextOfKin> nextOfKinOptional = nextOfKinRepository.findById(id);
        if (nextOfKinOptional.isPresent()) {
            NextOfKin newNextOfKin = nextOfKinOptional.get();
            newNextOfKin.setNFirstName(oldNextOfKin.getNFirstName());
            newNextOfKin.setNLastName(oldNextOfKin.getNLastName());
            newNextOfKin.setAddress(oldNextOfKin.getAddress());
            newNextOfKin.setLga(oldNextOfKin.getLga());
            newNextOfKin.setStreet(oldNextOfKin.getStreet());
            newNextOfKin.setState(oldNextOfKin.getState());
            newNextOfKin.setNPhoneNumber(oldNextOfKin.getNPhoneNumber());
            nextOfKinRepository.save(newNextOfKin);
            return Optional.of(newNextOfKin);
        } else throw new NoSuchElementException("Update failed");
    }
}
