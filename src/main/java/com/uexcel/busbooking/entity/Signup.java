package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Signup{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signupId;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String email;
    @Column(unique=true)
    private String phoneNumber;
    private String password;

    @OneToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private NextOfKin nextOfKin;
}
