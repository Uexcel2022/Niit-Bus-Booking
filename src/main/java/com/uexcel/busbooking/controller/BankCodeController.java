package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.entity.BankCode;
import com.uexcel.busbooking.service.BankCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BankCodeController {
    private final BankCodeService bankCodeService;

    public BankCodeController(BankCodeService bankCodeService) {
        this.bankCodeService = bankCodeService;
    }

    @PostMapping("/api/v1/add-bank-code")
    public ResponseEntity<String> addBankCode(@RequestBody Set<BankCode> bankCode) {
        return  ResponseEntity.ok().body(bankCodeService.addBankCode(bankCode));
    }

    @PostMapping("/api/v1/find-bank-code")
    public ResponseEntity<BankCode> findBankByCodeOrName(@RequestBody BankCode bankCode) {
        return ResponseEntity.ok().body(bankCodeService.findBankByNameOrCode(bankCode));
    }

    @PostMapping("/api/v1/update-bank-code/{nameOrCode}")
    public ResponseEntity<String> UpdateBankByCodeOrName(@PathVariable String nameOrCode, @RequestBody BankCode bankCode) {
        return ResponseEntity.ok().body(bankCodeService.updateBankByCodeOrName(nameOrCode,bankCode));
    }
}
