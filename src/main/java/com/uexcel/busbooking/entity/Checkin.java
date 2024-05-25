package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false,updatable = false)
    private String busCode;
    @Column(nullable = false,updatable = false)
    private String currentBusRouteId;

    @OneToOne(cascade = CascadeType.PERSIST,optional = false)
    private Booking booking;

    @Column(nullable = false)
    private LocalDate checkinDate = LocalDate.now();
}
