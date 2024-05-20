package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
    private LocalDate transactionDate = LocalDate.now();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserWallet wallet;

}
