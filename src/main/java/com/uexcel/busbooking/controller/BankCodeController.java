package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.entity.BankCode;
import com.uexcel.busbooking.service.BankCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class BankCodeController {
    private final BankCodeService bankCodeService;

    public BankCodeController(BankCodeService bankCodeService) {
        this.bankCodeService = bankCodeService;
    }

    @PostMapping("add-bank-code")
    public ResponseEntity<String> addBankCode(@RequestBody Set<BankCode> bankCode) {
        return  ResponseEntity.ok().body(bankCodeService.addBankCode(bankCode));
    }

    @PostMapping("find-bank-code")
    public ResponseEntity<BankCode> findBankByCodeOrName(@RequestBody BankCode bankCode) {
        return ResponseEntity.ok().body(bankCodeService.findBankByNameOrCode(bankCode));
    }

    @PostMapping("update-bank-code/{nameOrCode}")
    public ResponseEntity<String> UpdateBankByCodeOrName(@PathVariable String nameOrCode, @RequestBody BankCode bankCode) {
        return ResponseEntity.ok().body(bankCodeService.updateBankByCodeOrName(nameOrCode,bankCode));
    }
}
