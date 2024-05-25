package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.UseEmailPasswordDto;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public User findByUserByEmail(UseEmailPasswordDto useEmailPasswordDto) {
        return userRepository.findByEmail(useEmailPasswordDto.getEmail());
    }

    @Override
    public User findByUserById(String id) {
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
    public User updateUser(String id, User updatedUser) {
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
