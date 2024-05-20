package com.uexcel.busbooking.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookingInfoDto {
    private String fName;
    private String lName;
    private double amount;
    private String tickNumber;
    private String routeName;
    private LocalDate bookingDate;
    private String TickStatus;



}
