package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletInfoDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
import com.uexcel.busbooking.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService
                            ) {
        this.walletService = walletService;
    }

    @PostMapping("/api/v1/fund-wallet")
    public ResponseEntity<String> fundWallet(@RequestBody WalletFundingDto walletFundingDto){

        return ResponseEntity.ok().body(walletService.processWalletFunding(walletFundingDto));
    }

    @GetMapping("/api/v1/client-wallet/{clientId}")
    public ResponseEntity<WalletInfoDto> findWalletUserById(@PathVariable String clientId){
        return ResponseEntity.ok().body(walletService.findWalletByWalletNumber(clientId));

    }

    @GetMapping("/api/v1/client-wallet-trans/{walletId}")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(@PathVariable String walletId){
       return ResponseEntity.ok().body(walletService.findWalletTransByWalletNumber(walletId));

    }

    @GetMapping("/api/v1/client-wallet")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(){
        return ResponseEntity.ok().body(walletService.findClientWallet());

    }

    @PostMapping("/api/v1/client-wallet-transfer")
    public ResponseEntity<String> walletTransfer(@RequestBody Map<String,String> walletTransferData){
        return ResponseEntity.ok().body(walletService.walletTransfer(walletTransferData));

    }
}
