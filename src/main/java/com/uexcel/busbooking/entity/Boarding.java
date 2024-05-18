package com.uexcel.busbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Boarding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardingId;

    @OneToOne(cascade = CascadeType.DETACH,optional = false)
    private Booking booking;

    @ManyToOne(cascade = CascadeType.DETACH,optional = false)
    private Bus bus;

    @Column(nullable = false)
    private LocalDate boardedDate;
}
