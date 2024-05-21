package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(unique=true, nullable = false)
    private String email;
    @Column(unique=true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

//    @OneToOne(mappedBy = "user")
//    private NextOfKin nextOfKin;
//
//    @OneToOne(mappedBy = "user" )
//    private UserWallet userWallet;
}
