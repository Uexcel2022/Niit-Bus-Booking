package com.uexcel.busbooking.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookingInfoDto {
    private String fullName;
    private String gender;
    private double amount;
    private String tickNumber;
    private String routeName;
    private LocalDate bookingDate;
    private String TickStatus;



}
