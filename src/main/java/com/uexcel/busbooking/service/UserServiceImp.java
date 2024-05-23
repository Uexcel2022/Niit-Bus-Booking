package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final NextOfKinService nextOfKinService;

    public UserServiceImp(UserRepository userRepository,
                          NextOfKinService nextOfKinService) {
        this.userRepository = userRepository;
        this.nextOfKinService = nextOfKinService;

    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public User getUserByEmail(UseEmailPasswordDto useEmailPasswordDto) {
        return userRepository.findByEmail(useEmailPasswordDto.getEmail());
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> signup = userRepository.findById(id);
        if (signup.isPresent()) {
            return signup.get();
        }else  throw new NoSuchElementException("User not found");
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public NextOfKin getNextOfKinById(Long id) {
        Optional<NextOfKin> nextOfKin = nextOfKinService.getNextOfKinRepository().findById(id);
        if (nextOfKin.isPresent()) {
            return nextOfKin.get();
        }else  throw new NoSuchElementException("next of kin not found");
    }

    @Override
    public NextOfKin updateNextOfKin(Long id, NextOfKin updatedNextOfKin) {

        Optional<NextOfKin> nextOfKinOptional = nextOfKinService.getNextOfKinRepository().findById(id);
        if (nextOfKinOptional.isPresent()) {
            NextOfKin oldNextOfKin = nextOfKinOptional.get();
            oldNextOfKin.setNFirstName(updatedNextOfKin.getNFirstName());
            oldNextOfKin.setNLastName(updatedNextOfKin.getNLastName());
            oldNextOfKin.setAddress(updatedNextOfKin.getAddress());
            oldNextOfKin.setLga(updatedNextOfKin.getLga());
            oldNextOfKin.setStreet(updatedNextOfKin.getStreet());
            oldNextOfKin.setState(updatedNextOfKin.getState());
            oldNextOfKin.setNPhoneNumber(updatedNextOfKin.getNPhoneNumber());
            nextOfKinService.getNextOfKinRepository().save(oldNextOfKin);
            return oldNextOfKin;
        } else throw new NoSuchElementException("Update failed");
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> signupOptional = userRepository.findById(id);
        if (signupOptional.isPresent()) {
            User oldUser = signupOptional.get();
            oldUser.setFirstName(updatedUser.getFirstName());
            oldUser.setLastName(updatedUser.getLastName());
            oldUser.setEmail(updatedUser.getEmail());
            oldUser.setPassword(updatedUser.getPassword());
            oldUser.setPhoneNumber(updatedUser.getPhoneNumber());
          return userRepository.save(oldUser);

        } else throw new NoSuchElementException("Update failed");
    }


//    @Override
//    public ResponseDto deleteUser(Long userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            userRepository.deleteById(userId);
//            ResponseDto responseDto = new ResponseDto();
//            responseDto.setResponse("User deleted successfully");
//            return responseDto;
//        } else throw new NoSuchElementException("User not found");
//    }



}
