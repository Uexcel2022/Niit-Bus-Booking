package com.uexcel.busbooking.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BusRouteDto {
    private String busCode;
    private int busCapacity;
    private String serviceStartDate = LocalDate.now().toString();
    private String serviceEndDate;
    private String brand;
    private String model;
    private String routeName;
    private double price;

}
