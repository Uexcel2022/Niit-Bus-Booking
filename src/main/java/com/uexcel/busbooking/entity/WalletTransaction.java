package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionType;
    private String accountNumber;
    private String cCNumber;
    private double amount;
    private String transactionDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserWallet wallet;

}
