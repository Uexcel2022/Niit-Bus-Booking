package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class NextOfKin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nFirstName;
    @Column(nullable = false)
    private String nLastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String lga;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String nPhoneNumber;

    @OneToOne(optional = false,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
}
