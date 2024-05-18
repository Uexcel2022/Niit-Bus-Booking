package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signupId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique=true, nullable = false)
    private String email;
    @Column(unique=true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

    @OneToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private NextOfKin nextOfKin;
}
