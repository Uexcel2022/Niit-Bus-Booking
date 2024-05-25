package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.UserWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.repository.UserWalletRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserWalletServiceImp implements UserWalletService {
    private  final UserWalletRepository userWalletRepository;
    private  final WalletTransactionService walletTransactionService;
    public UserWalletServiceImp(UserWalletRepository userWalletRepository,
                                WalletTransactionService walletTransactionService) {
        this.userWalletRepository = userWalletRepository;
        this.walletTransactionService = walletTransactionService;
    }
    @Override
    public UserWalletRepository getUserWalletRepository() {
        return userWalletRepository;
    }

    @Override
    public ResponseDto processWalletFunding(WalletFundingDto walletFundingDto) {

        Optional<UserWallet> userWallet = userWalletRepository
                .findById(walletFundingDto.getId());

        if (userWallet.isEmpty()) {
            throw new NoSuchElementException("Invalid wallet code");
        }
        UserWallet uW = userWallet.get();

        double balance = uW.getBalance();
        double newBalance = balance + walletFundingDto.getAmount();
        WalletTransaction walletTransaction = new WalletTransaction();
        walletTransaction.setFullName(walletFundingDto.getFullName());
        walletTransaction.setTransactionType(walletFundingDto.getTransactionType());
        walletTransaction.setAccountNumber(walletFundingDto.getAccountNumber());
        walletTransaction.setCCNumber(walletFundingDto.getCCNumber());
        walletTransaction.setAmount(walletFundingDto.getAmount());
        uW.setBalance(newBalance);
        walletTransaction.setWallet(uW);
        userWalletRepository.save(uW);
        walletTransactionService.getUserWalletRepository().save(walletTransaction);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Transaction successful");
        return responseDto;

    }

    @Override
    public UserWallet findWalletByUserId(String userId) {
        return userWalletRepository.findUserWalletByUserId(userId);
    }

}
