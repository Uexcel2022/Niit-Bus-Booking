package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletInfoDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImp implements WalletService {
    private final String statusCode404 = "404";
    private final Repos repos;
    public WalletServiceImp(Repos repos) {

        this.repos = repos;
    }

    //Tested ok
    @Override
    @Transactional
    public ResponseDto processWalletFunding(WalletFundingDto walletFundingDto) {

        ClientWallet clientWallet = repos.getClientWalletRepository()
                .findByWalletNumber(walletFundingDto.getWalletNumber());

        if (clientWallet==null) {
            throw new CustomException("Invalid wallet number", statusCode404);
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
        repos.getClientWalletRepository().save(clientWallet);
        repos.getWallTransactionRepository().save(walletTransaction);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Transaction successful");
        return responseDto;

    }

    //Tested ok
    @Override
    @Transactional
    public WalletInfoDto findWalletByWalletNumber(String walletNumber) {
        WalletInfoDto walletInfoDto = new WalletInfoDto();
        ClientWallet clientWallet = repos.getClientWalletRepository().findByWalletNumber(walletNumber);
        if (clientWallet == null) {
            throw new CustomException("Wallet not found", statusCode404);
        }
        walletInfoDto.setWalletId(clientWallet.getId());
        walletInfoDto.setWalletNumber(clientWallet.getWalletNumber());
        walletInfoDto.setBalance(clientWallet.getBalance());
        walletInfoDto.setWalletStatus(clientWallet.getStatus());
        walletInfoDto.setClientId(clientWallet.getClient().getId());
        walletInfoDto.setFullName(clientWallet.getClient().getFullName());
        walletInfoDto.setBirthDate(clientWallet.getClient().getBirthDate());
        walletInfoDto.setEmail(clientWallet.getClient().getEmail());
        walletInfoDto.setPhoneNumber(clientWallet.getClient().getPhoneNumber());
        walletInfoDto.setClientStatus(clientWallet.getClient().getStatus());


        return walletInfoDto;
    }

    //Tested ok
    @Override
    public List<WalletTransactionInfoDto> findWalletTransByWalletNumber(String walletNumber) {
        ClientWallet clientWallet = repos.getClientWalletRepository().findByWalletNumber(walletNumber);
        if (clientWallet == null) {
            throw new CustomException("Wallet not found", statusCode404);
        }
        List<WalletTransaction> walletTransactions = repos.getWallTransactionRepository().findByWalletNumber(walletNumber);
        return checkResult(walletTransactions);
    }

    @Override
    public List<WalletTransactionInfoDto> findClientWallet() {
        List<WalletTransaction> walletTransactions = repos.getWallTransactionRepository().findAll();
        return checkResult(walletTransactions);

    }



    private List<WalletTransactionInfoDto> checkResult(List<WalletTransaction> walletTransactions) {
        if (walletTransactions.isEmpty()) {
            throw new CustomException("Not found wallet transaction", statusCode404);
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
                walletTransaction.getWallet().getClient().getFullName());
        return walletTransactionInfoDto;
    }

}
