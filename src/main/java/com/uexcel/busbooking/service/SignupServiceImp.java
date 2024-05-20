package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.QueryUser;
import com.uexcel.busbooking.dto.RegistrationData;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.entity.UserWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import com.uexcel.busbooking.repository.UserRepository;
import com.uexcel.busbooking.repository.UserWalletRepository;
import com.uexcel.busbooking.repository.WallTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SignupServiceImp implements SignupService {

    private final UserRepository userRepository;
    private final NextOfKinRepository nextOfKinRepository;
    private final UserWalletRepository userWalletRepository;
    private final WallTransactionRepository wallTransactionRepository;

    public SignupServiceImp(UserRepository userRepository,
                            NextOfKinRepository nextOfKinRepository,
                            UserWalletRepository userWalletRepository,
                            WallTransactionRepository wallTransactionRepository) {
        this.userRepository = userRepository;
        this.nextOfKinRepository = nextOfKinRepository;
        this.userWalletRepository = userWalletRepository;
        this.wallTransactionRepository = wallTransactionRepository;
    }
    public User processSignup(RegistrationData registrationData) {

        User user = new User();
        NextOfKin nextOfKin = new NextOfKin();
        UserWallet userWallet = new UserWallet();

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

        userWallet.setBalance(0.0);
        userWallet.setStatus("active");
        user.setUserWallet(userWallet);

        userWalletRepository.save(userWallet);
        nextOfKinRepository.save(nextOfKin);
       return userRepository.save(user);
    }

    @Override
    public User getUser(QueryUser queryUser) {
        return userRepository.findByEmail(queryUser.getEmail());
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

    @Override
    public ResponseDto processWalletFunding(WalletFundingDto walletFundingDto) {

        UserWallet userWallet = userWalletRepository.findByWalletCode(walletFundingDto.getWalletCode());

        if (userWallet == null) {
            throw new NoSuchElementException("Invalid wallet code");
        }
            double balance = userWallet.getBalance();
            double newBalance = balance + walletFundingDto.getAmount();
            WalletTransaction walletTransaction = new WalletTransaction();
            walletTransaction.setTransactionType(walletFundingDto.getTransactionType());
            walletTransaction.setAccountNumber(walletFundingDto.getAccountNumber());
            walletTransaction.setCCNumber(walletFundingDto.getCCNumber());
            walletTransaction.setAmount(walletFundingDto.getAmount());
            userWallet.setBalance(newBalance);
            walletTransaction.setWallet(userWallet);
            userWalletRepository.save(userWallet);
            wallTransactionRepository.save(walletTransaction);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResponse("Transaction successful");
            return responseDto;

    }
}
