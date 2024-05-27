//package com.uexcel.busbooking.service;
//
//import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
//import com.uexcel.busbooking.entity.ClientWallet;
//import com.uexcel.busbooking.entity.WalletTransaction;
//import com.uexcel.busbooking.exception.CustomException;
//import com.uexcel.busbooking.repository.WallTransactionRepository;
//import lombok.Getter;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Getter
//public class WalletTransactionServiceImp implements WalletTransactionService {
//    private final WallTransactionRepository wallTransactionRepository;
//    private final ClientWalletService clientWalletService;
//    public WalletTransactionServiceImp(WallTransactionRepository wallTransactionRepository, ClientWalletService clientWalletService) {
//        this.wallTransactionRepository = wallTransactionRepository;
//        this.clientWalletService = clientWalletService;
//    }
//    @Override
//    public WallTransactionRepository getUserWalletRepository() {
//        return wallTransactionRepository;
//    }
//
//    @Override
//    public List<WalletTransactionInfoDto> findWalletTransByWalletCode(String walletCode) {
//        ClientWallet clientWallet = clientWalletService.getUserWalletRepository().findByWalletCode(walletCode);
//        if (clientWallet == null) {
//            throw new CustomException("Wallet not found","404");
//        }
//        List<WalletTransaction> walletTransactions =wallTransactionRepository.findByWalletId(clientWallet.getId());
//        if(walletTransactions.isEmpty()){
//            throw  new CustomException("Not found wallet transaction","404");
//        }
//
//        List<WalletTransactionInfoDto> walletTransactionInfoDtos = new ArrayList<>();
//
//        for(WalletTransaction walletTransaction : walletTransactions){
//            WalletTransactionInfoDto walletTransactionInfoDto = getWalletTransactionInfoDto(walletTransaction);
//            walletTransactionInfoDtos.add(walletTransactionInfoDto);
//        }
//
//
//        return walletTransactionInfoDtos;
//    }
//
//    private static WalletTransactionInfoDto getWalletTransactionInfoDto(WalletTransaction walletTransaction) {
//        WalletTransactionInfoDto walletTransactionInfoDto = new WalletTransactionInfoDto();
//        walletTransactionInfoDto.setTransactionId(walletTransaction.getId());
//        walletTransactionInfoDto.setWalletCode(walletTransaction.getWallet().getWalletCode());
//        walletTransactionInfoDto.setTransactionType(walletTransaction.getTransactionType());
//        walletTransactionInfoDto.setAccountNumber(walletTransaction.getAccountNumber());
//        walletTransactionInfoDto.setCCNumber(walletTransaction.getCCNumber());
//        walletTransactionInfoDto.setCCType(walletTransaction.getCCType());
//        walletTransactionInfoDto.setBank(walletTransaction.getBank());
//        walletTransactionInfoDto.setPaymentDate(walletTransaction.getTransactionDate());
//        walletTransactionInfoDto.setAmount(walletTransaction.getAmount());
//        walletTransactionInfoDto.setPayer(walletTransaction.getFullName());
//        walletTransactionInfoDto.setClientName(
//                walletTransaction.getWallet().getClient()
//                        .getFirstName()+ " "+ walletTransaction.getWallet().getClient().getLastName());
//        return walletTransactionInfoDto;
//    }
//
//
//}
