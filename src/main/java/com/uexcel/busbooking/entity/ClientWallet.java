package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClientWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    String walletNumber;
    private double balance;
    private String status;

    @OneToOne(cascade = CascadeType.ALL, optional = false,fetch = FetchType.EAGER)
    private Client client;
}
