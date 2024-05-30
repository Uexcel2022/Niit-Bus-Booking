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
@RequestMapping("/api/v1")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService
                            ) {
        this.walletService = walletService;
    }

    @PostMapping("fund-wallet")
    public ResponseEntity<String> fundWallet(@RequestBody WalletFundingDto walletFundingDto){

        return ResponseEntity.ok().body(walletService.processWalletFunding(walletFundingDto));
    }

    @GetMapping("client-wallet/{clientId}")
    public ResponseEntity<WalletInfoDto> findWalletUserById(@PathVariable String clientId){
        return ResponseEntity.ok().body(walletService.findWalletByWalletNumber(clientId));

    }

    @GetMapping("client-wallet-trans/{walletId}")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(@PathVariable String walletId){
       return ResponseEntity.ok().body(walletService.findWalletTransByWalletNumber(walletId));

    }

    @GetMapping("client-wallet")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(){
        return ResponseEntity.ok().body(walletService.findClientWallet());

    }

    @PostMapping("client-wallet-transfer")
    public ResponseEntity<String> walletTransfer(@RequestBody Map<String,String> walletTransferData){
        return ResponseEntity.ok().body(walletService.walletTransfer(walletTransferData));

    }
}
