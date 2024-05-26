package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.service.ClientWalletService;
import com.uexcel.busbooking.service.WalletTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserWalletController {
    private final ClientWalletService clientWalletService;
    private final WalletTransactionService walletTransactionService;

    public UserWalletController(ClientWalletService clientWalletService, WalletTransactionService walletTransactionService) {
        this.clientWalletService = clientWalletService;
        this.walletTransactionService = walletTransactionService;
    }

    @PostMapping("/api/v1/fund-wallet")
    public ResponseEntity<ResponseDto> fundWallet(@RequestBody WalletFundingDto walletFundingDto){

        return ResponseEntity.ok().body(clientWalletService.processWalletFunding(walletFundingDto));
    }

    @GetMapping("/api/v1/client-wallet/{userId}")
    public ResponseEntity<ClientWallet> findWalletUserById(@PathVariable String userId){
        return ResponseEntity.ok().body(clientWalletService.findWalletByUserId(userId));

    }

    @GetMapping("/api/v1/client-wallet-trans/{walletId}")
    public ResponseEntity<List<WalletTransaction>> findWalletTransactionById(@PathVariable String walletId){
       return ResponseEntity.ok().body(walletTransactionService.findWalletTransByWalletId(walletId));

    }

}
