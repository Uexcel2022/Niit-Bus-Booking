package com.uexcel.busbooking.dto;

import lombok.Data;

@Data
public class WalletFundingDto {
    private String fullName;
    private String bank;
    private String bankCode;
    private String walletNumber;
    private String transactionType;
    private String accountNumber;
    private String cCNumber;
    private double amount;
}
