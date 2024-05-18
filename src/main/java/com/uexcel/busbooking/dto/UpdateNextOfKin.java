package com.uexcel.busbooking.dto;

import lombok.Data;

@Data
public class UpdateNextOfKin {
    private Long id;
    private String nFirstName;
    private String nLastName;
    private String address;
    private String lga;
    private String street;
    private String state;
    private String nPhoneNumber;
}
