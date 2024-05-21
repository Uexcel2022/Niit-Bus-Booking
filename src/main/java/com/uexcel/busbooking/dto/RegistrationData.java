package com.uexcel.busbooking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationData {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String password;
    private String nFirstName;
    private String nLastName;
    private String address;
    private String lga;
    private String street;
    private String state;
    private String nPhoneNumber;

}
