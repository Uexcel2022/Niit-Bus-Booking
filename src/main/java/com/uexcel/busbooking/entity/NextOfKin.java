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
    private String firstName;
    private String lastName;
    private String address;
    private String LGA;
    private String string;
    private String state;
    private String phoneNumber;
}
