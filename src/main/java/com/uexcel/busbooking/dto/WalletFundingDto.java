package com.uexcel.busbooking.dto;

import lombok.Data;

@Data
public class WalletFundingDto {
    private String fullName;
    private String walletCode;
    private String transactionType;
    private String accountNumber;
    private String cCNumber;
    private double amount;
}
