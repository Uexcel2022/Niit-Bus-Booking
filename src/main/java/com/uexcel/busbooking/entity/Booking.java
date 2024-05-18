package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(unique = true, nullable = false)
    private String tickNumber;
    @Column(nullable = false)
    private LocalDate bookingDate;
    @Column(nullable = false)
    private String TickStatus;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Route route;

}
