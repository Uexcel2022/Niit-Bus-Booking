package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String ticketNumber = UUID.randomUUID().toString();
    @Column(nullable = false)
    private LocalDate bookingDate = LocalDate.now();
    @Column(nullable = false)
    private String ticketStatus;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,optional = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,optional = false)
    private Route route;

}
