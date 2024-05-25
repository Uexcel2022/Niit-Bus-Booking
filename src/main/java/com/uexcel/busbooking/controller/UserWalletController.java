package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.UserWallet;
import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.service.UserWalletService;
import com.uexcel.busbooking.service.WalletTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserWalletController {
    private final UserWalletService userWalletService;
    private final WalletTransactionService walletTransactionService;

    public UserWalletController(UserWalletService userWalletService, WalletTransactionService walletTransactionService) {
        this.userWalletService = userWalletService;
        this.walletTransactionService = walletTransactionService;
    }

    @PostMapping("/api/v1/fund_wallet")
    public ResponseEntity<ResponseDto> fundWallet(@RequestBody WalletFundingDto walletFundingDto){

        return ResponseEntity.ok().body(userWalletService.processWalletFunding(walletFundingDto));
    }

    @GetMapping("/api/v1/user_wallet/{userId}")
    public ResponseEntity<UserWallet> findWalletUserById(@PathVariable String userId){
        return ResponseEntity.ok().body(userWalletService.findWalletByUserId(userId));

    }

    @GetMapping("/api/v1/user_wallet_trans/{walletId}")
    public ResponseEntity<List<WalletTransaction>> findWalletTransactionById(@PathVariable String walletId){
       return ResponseEntity.ok().body(walletTransactionService.findWalletTransByWalletId(walletId));

    }

}
