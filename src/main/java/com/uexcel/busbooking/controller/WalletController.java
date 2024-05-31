package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletInfoDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.service.WalletService;
import com.uexcel.busbooking.utils.Repos;
import com.uexcel.busbooking.utils.UtilsToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class WalletController {
    private static final Logger log = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    @Autowired
    Repos repos;

    UtilsToken ut = new UtilsToken();

    public WalletController(WalletService walletService
                            ) {
        this.walletService = walletService;
    }

    @PostMapping("fund-wallet")
    public ResponseEntity<String> fundWallet(@RequestBody WalletFundingDto walletFundingDto){

        return ResponseEntity.ok().body(walletService.processWalletFunding(walletFundingDto));
    }

    @GetMapping("client-wallet/{clientId}")
    public ResponseEntity<WalletInfoDto> findWalletUserById(@PathVariable String clientId,
                                                            @RequestHeader("rapid-transit") String token) throws Exception {
        log.info("token");
        Client client = repos.getClientRepository().findByEmail(ut.retrieveEmailFromToken(token));
//        walletService.findWalletTransByWalletNumber(clientId);
        if (client != null) log.info(client.toString());
        else log.info("absent");
        return ResponseEntity.ok().body(walletService.findWalletByWalletNumber(clientId));

    }

    @GetMapping("client-wallet-trans/{walletId}")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(
            @PathVariable String walletId,
            @RequestHeader("rapid-transit") String token) throws Exception {
        log.info(token);
        Client client = repos.getClientRepository().findByEmail(ut.retrieveEmailFromToken(token));
        walletService.findWalletTransByWalletNumber(walletId);
        if (client != null) log.info(client.toString());
        else log.info("absent");
        return ResponseEntity.ok().body(walletService.findWalletTransByWalletNumber(walletId));

    }

    @GetMapping("client-wallet")
    public ResponseEntity<List<WalletTransactionInfoDto>> findWalletTransactionByCode(){
        return ResponseEntity.ok().body(walletService.findAllClientWallet());

    }

    @PostMapping("client-wallet-transfer")
    public ResponseEntity<String> walletTransfer(
            @RequestBody Map<String,String> walletTransferData,
            @RequestHeader("rapid-transits") String token
    ){
        return ResponseEntity.ok().body(walletService.walletTransfer(walletTransferData));

    }
}
