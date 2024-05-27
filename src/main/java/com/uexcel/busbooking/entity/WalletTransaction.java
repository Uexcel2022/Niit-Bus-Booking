package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String transactionType;
    private String accountNumber;
    private String cCNumber;
    private String cCType;
    @Column(nullable = false)
    private String bank;
    @Column(nullable = false)
    private double amount;
    private LocalDate transactionDate = LocalDate.now();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "walletNumber",referencedColumnName = "walletNumber", nullable = false,
            updatable = false, unique = false
    )
    private ClientWallet wallet;

}
