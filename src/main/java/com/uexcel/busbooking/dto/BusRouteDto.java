package com.uexcel.busbooking.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BusRouteDto {
    private String busCode;
    private int busCapacity;
    private LocalDate serviceStartDate = LocalDate.now();
    private LocalDate serviceEndDate;
    private String brand;
    private String model;
    private String routeName;
    private double price;

}
