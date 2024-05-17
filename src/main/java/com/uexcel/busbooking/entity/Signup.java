package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Signup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signupId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToOne(optional = false,fetch = FetchType.EAGER)
    private NextOfKin nextOfKin;
}
