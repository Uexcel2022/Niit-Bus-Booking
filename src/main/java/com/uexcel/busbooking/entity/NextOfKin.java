package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class NextOfKin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nextOfKinId;
    private String nFirstName;
    private String nLastName;
    private String address;
    private String lga;
    private String street;
    private String state;
    private String nPhoneNumber;
}
