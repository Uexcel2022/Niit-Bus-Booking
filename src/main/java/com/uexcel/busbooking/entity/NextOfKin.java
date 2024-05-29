package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class NextOfKin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String nFullName;
//    @Column(nullable = false)
//    private String nGender;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String lga;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String nPhoneNumber;
    private String status;

    @OneToOne(optional = false,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Client client;
}
