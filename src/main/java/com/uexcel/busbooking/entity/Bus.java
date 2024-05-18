package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;
    @Column(nullable = false)
    private String busCode;
    @Column(nullable = false)
    private String busCapacity;
    @Column(nullable = false)
    private String serviceStartDate;
    @Column(nullable = false)
    private String serviceEndDate;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;


}
