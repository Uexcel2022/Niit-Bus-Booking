package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.repository.ClientWalletRepository;
import com.uexcel.busbooking.repository.WallTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImp implements WalletService {
    private final ClientWalletRepository clientWalletRepository;
    private final WallTransactionRepository wallTransactionRepository;

    public WalletServiceImp(ClientWalletRepository clientWalletRepository,
                            WallTransactionRepository wallTransactionRepository

    ) {
        this.clientWalletRepository = clientWalletRepository;
        this.wallTransactionRepository = wallTransactionRepository;
    }

    @Override
    public ClientWalletRepository getClientWalletRepository() {
        return clientWalletRepository;
    }

    @Override
    public WallTransactionRepository getWallTransactionRepository() {
        return wallTransactionRepository;
    }


    //Tested ok
    @Override
    public ResponseDto processWalletFunding(WalletFundingDto walletFundingDto) {

        ClientWallet clientWallet = clientWalletRepository
                .findByWalletNumber(walletFundingDto.getWalletNumber());

        if (clientWallet==null) {
            throw new CustomException("Invalid wallet number", "404");
        }


        double balance = clientWallet.getBalance();
        double newBalance = balance + walletFundingDto.getAmount();
        WalletTransaction walletTransaction = new WalletTransaction();
        walletTransaction.setFullName(walletFundingDto.getFullName());
        walletTransaction.setTransactionType(walletFundingDto.getTransactionType());
        walletTransaction.setAccountNumber(walletFundingDto.getAccountNumber());
        walletTransaction.setCCNumber(walletFundingDto.getCCNumber());
        walletTransaction.setAmount(walletFundingDto.getAmount());
        walletTransaction.setBank(walletFundingDto.getBank());
        clientWallet.setBalance(newBalance);
        walletTransaction.setWallet(clientWallet);
        clientWalletRepository.save(clientWallet);
        wallTransactionRepository.save(walletTransaction);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Transaction successful");
        return responseDto;

    }

    //Tested ok
    @Override
    public ClientWallet findWalletByWalletNumber(String walletNumber) {
        ClientWallet clientWallet = clientWalletRepository.findByWalletNumber(walletNumber);
        if (clientWallet == null) {
            throw new CustomException("Wallet not found", "404");
        }
        return clientWallet;
    }

    //Tested ok
    @Override
    public List<WalletTransactionInfoDto> findWalletTransByWalletNumber(String walletNumber) {
        ClientWallet clientWallet = clientWalletRepository.findByWalletNumber(walletNumber);
        if (clientWallet == null) {
            throw new CustomException("Wallet not found", "404");
        }
        List<WalletTransaction> walletTransactions = wallTransactionRepository.findByWalletNumber(walletNumber);
        if (walletTransactions.isEmpty()) {
            throw new CustomException("Not found wallet transaction", "404");
        }

        List<WalletTransactionInfoDto> walletTransactionInfoDtos = new ArrayList<>();

        for (WalletTransaction walletTransaction : walletTransactions) {
            WalletTransactionInfoDto walletTransactionInfoDto = getWalletTransactionInfoDto(walletTransaction);
            walletTransactionInfoDtos.add(walletTransactionInfoDto);
        }


        return walletTransactionInfoDtos;
    }

    private static WalletTransactionInfoDto getWalletTransactionInfoDto(WalletTransaction walletTransaction) {
        WalletTransactionInfoDto walletTransactionInfoDto = new WalletTransactionInfoDto();
        walletTransactionInfoDto.setTransactionId(walletTransaction.getId());
        walletTransactionInfoDto.setWalletCode(walletTransaction.getWallet().getWalletNumber());
        walletTransactionInfoDto.setTransactionType(walletTransaction.getTransactionType());
        walletTransactionInfoDto.setAccountNumber(walletTransaction.getAccountNumber());
        walletTransactionInfoDto.setCCNumber(walletTransaction.getCCNumber());
        walletTransactionInfoDto.setCCType(walletTransaction.getCCType());
        walletTransactionInfoDto.setBank(walletTransaction.getBank());
        walletTransactionInfoDto.setPaymentDate(walletTransaction.getTransactionDate());
        walletTransactionInfoDto.setAmount(walletTransaction.getAmount());
        walletTransactionInfoDto.setPayer(walletTransaction.getFullName());
        walletTransactionInfoDto.setClientName(
                walletTransaction.getWallet().getClient()
                        .getFirstName()+ " "+ walletTransaction.getWallet().getClient().getLastName());
        return walletTransactionInfoDto;
    }

}
