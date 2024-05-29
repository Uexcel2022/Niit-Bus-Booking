package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.BankCode;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class BankCodeServiceImp implements BankCodeService {

    private final Repos repos;

    public BankCodeServiceImp(Repos repos) {
        this.repos = repos;
    }

    @Override
    public String addBankCode(Set<BankCode> bankCodes) {


        for (BankCode bankCode : bankCodes) {
            BankCode bank = repos.getBankCodeRepository().findByBankCodeOrBankName(
                    bankCode.getBankCode(),bankCode.getBankName());

                    if(bank != null){
                throw new CustomException("Bank exist. " +"Name: "+bank.getBankName() +"; Code: "+bank.getBankCode(),"400");
            }
            repos.getBankCodeRepository().save(bankCode);
        }

        return "Inserted successfully";
    }

    @Override
    public BankCode findBankByNameOrCode(BankCode bankCode) {
        BankCode bank = repos.getBankCodeRepository().findByBankCodeOrBankName(bankCode.getBankCode(),bankCode.getBankName());
        if(bank == null){
            throw new CustomException("Bank not found.","400");
        }
        return bank;
    }

    public String updateBankByCodeOrName(String nameCode, BankCode bankCode) {
        BankCode bank = repos.getBankCodeRepository().findByBankCodeOrBankName(nameCode,nameCode);
        if(bank == null){
            throw new CustomException("Bank not found.","400");
        }
        if(bankCode.getBankCode() != null){
            bank.setBankCode(bankCode.getBankCode());
        }
        if(bankCode.getBankName() != null){
            bank.setBankName(bankCode.getBankName());
        }

        repos.getBankCodeRepository().save(bank);

        return "Bank updated successfully";
    }

}
