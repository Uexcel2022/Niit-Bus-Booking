package com.uexcel.busbooking.dto;

import lombok.Data;

@Data
public class ClientDetailsDto {
    private String ClientId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String walletNumber;
    private double balance;
    private String nextKinName;
    private String nextKinPhone;
    private String status;


}
