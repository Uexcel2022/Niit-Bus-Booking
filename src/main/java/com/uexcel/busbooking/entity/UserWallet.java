package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class UserWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String walletCode = UUID.randomUUID().toString();
    private double balance;
    private String status;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private User user;
}
