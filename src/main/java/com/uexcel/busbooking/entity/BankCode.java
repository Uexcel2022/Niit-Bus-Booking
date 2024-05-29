package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BankCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique=true)
    private String bankName;
    @Column(unique=true)
    private String bankCode;
}
