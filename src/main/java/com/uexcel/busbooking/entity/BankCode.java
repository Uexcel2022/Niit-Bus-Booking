package com.uexcel.busbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BankCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String bankName;
    private String bankCode;
}
