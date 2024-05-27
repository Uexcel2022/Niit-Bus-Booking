package com.uexcel.busbooking.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class WalletTransactionInfoDto {
    private String transactionId;
    private String payer;
    private String transactionType;
    private String accountNumber;
    private String cCNumber;
    private double amount;
    private LocalDate paymentDate;
    private String walletCode;
    private String clientName;
    private String cCType;
    private String bank;
}
