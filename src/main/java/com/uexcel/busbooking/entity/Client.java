package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String fullName;
//    @Column(nullable = false)
//    private String gender;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(unique=true, nullable = false)
    private String email;
    private Boolean emailVerified = false;
    @Column(unique=true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    private String status;

}
