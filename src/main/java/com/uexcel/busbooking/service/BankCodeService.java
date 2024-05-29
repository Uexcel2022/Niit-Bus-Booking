package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.BankCode;


import java.util.Set;

public interface BankCodeService {
    String addBankCode(Set<BankCode> bankCodeList);
    BankCode findBankByNameOrCode(BankCode bankCode);
    String updateBankByCodeOrName(String code, BankCode bankCode);
}
