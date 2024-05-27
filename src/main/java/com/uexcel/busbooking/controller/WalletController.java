package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService
                            ) {
        this.walletService = walletService;
    }

    @PostMapping("/api/v1/fund-wallet")
    public ResponseEntity<ResponseDto> fundWallet(@RequestBody WalletFundingDto walletFundingDto){

        return ResponseEntity.ok().body(walletService.processWalletFunding(walletFundingDto));
    }

    @GetMapping("/api/v1/client-wallet/{userId}")
    public ResponseEntity<ClientWallet> findWalletUserById(@PathVariable String userId){
        return ResponseEntity.ok().body(walletService.findWalletByWalletNumber(userId));

    }

    @GetMapping("/api/v1/client-wallet-trans/{walletId}")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(@PathVariable String walletId){
       return ResponseEntity.ok().body(walletService.findWalletTransByWalletNumber(walletId));

    }

}
