package com.uexcel.busbooking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WalletInfoDto {

    private String walletId;
    private String walletNumber;
    private double balance;
    private String walletStatus;
    private String clientId;
    private String fullName;
    private LocalDate birthDate;
    private String  email;
    private String phoneNumber;
    private String  clientStatus;

}
