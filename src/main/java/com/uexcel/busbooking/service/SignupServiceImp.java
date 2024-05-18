package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import com.uexcel.busbooking.repository.SignupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SignupServiceImp implements SignupService {

    private final   SignupRepository signupRepository;
    private final NextOfKinRepository nextOfKinRepository;

    public SignupServiceImp(SignupRepository signupRepository,
                            NextOfKinRepository nextOfKinRepository) {
        this.signupRepository = signupRepository;
        this.nextOfKinRepository = nextOfKinRepository;
    }
    public User processSignup(RegistrationData registrationData) {
        User user = new User();
        NextOfKin nextOfKin = new NextOfKin();
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
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
        user.setNextOfKin(nextOfKin);
        nextOfKinRepository.save(nextOfKin);
       return signupRepository.save(user);
    }

    @Override
    public User getUser(QueryUser queryUser) {
        return signupRepository.findByEmail(queryUser.getEmail());
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> signup = signupRepository.findById(id);
        if (signup.isPresent()) {
            return signup.get();
        }else  throw new NoSuchElementException("User not found");
    }

    @Override
    public List<User> findAllUsers() {
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
    public NextOfKin updateNextOfKin(Long id, NextOfKin updatedNextOfKin) {

        Optional<NextOfKin> nextOfKinOptional = nextOfKinRepository.findById(id);
        if (nextOfKinOptional.isPresent()) {
            NextOfKin oldNextOfKin = nextOfKinOptional.get();
            oldNextOfKin.setNFirstName(updatedNextOfKin.getNFirstName());
            oldNextOfKin.setNLastName(updatedNextOfKin.getNLastName());
            oldNextOfKin.setAddress(updatedNextOfKin.getAddress());
            oldNextOfKin.setLga(updatedNextOfKin.getLga());
            oldNextOfKin.setStreet(updatedNextOfKin.getStreet());
            oldNextOfKin.setState(updatedNextOfKin.getState());
            oldNextOfKin.setNPhoneNumber(updatedNextOfKin.getNPhoneNumber());
            nextOfKinRepository.save(oldNextOfKin);
            return oldNextOfKin;
        } else throw new NoSuchElementException("Update failed");
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> signupOptional = signupRepository.findById(id);
        if (signupOptional.isPresent()) {
            User oldUser = signupOptional.get();
            oldUser.setFirstName(updatedUser.getFirstName());
            oldUser.setLastName(updatedUser.getLastName());
            oldUser.setEmail(updatedUser.getEmail());
            oldUser.setPassword(updatedUser.getPassword());
            oldUser.setPhoneNumber(updatedUser.getPhoneNumber());
          return signupRepository.save(oldUser);

        } else throw new NoSuchElementException("Update failed");
    }
}
