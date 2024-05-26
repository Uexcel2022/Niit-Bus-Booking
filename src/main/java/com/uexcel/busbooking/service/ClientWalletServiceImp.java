package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.repository.ClientWalletRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientWalletServiceImp implements ClientWalletService {
    private  final ClientWalletRepository clientWalletRepository;
    private  final WalletTransactionService walletTransactionService;
    public ClientWalletServiceImp(ClientWalletRepository clientWalletRepository,
                                  WalletTransactionService walletTransactionService) {
        this.clientWalletRepository = clientWalletRepository;
        this.walletTransactionService = walletTransactionService;
    }
    @Override
    public ClientWalletRepository getUserWalletRepository() {
        return clientWalletRepository;
    }

    @Override
    public ResponseDto processWalletFunding(WalletFundingDto walletFundingDto) {

        Optional<ClientWallet> userWallet = clientWalletRepository
                .findById(walletFundingDto.getId());

        if (userWallet.isEmpty()) {
            throw new NoSuchElementException("Invalid wallet code");
        }
        ClientWallet uW = userWallet.get();

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
        clientWalletRepository.save(uW);
        walletTransactionService.getUserWalletRepository().save(walletTransaction);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Transaction successful");
        return responseDto;

    }

    @Override
    public ClientWallet findWalletByUserId(String userId) {
        return clientWalletRepository.findUserWalletByUserId(userId);
    }

}
