package com.uexcel.busbooking.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BusCheckinInfoDto {
    private String busCode;
    private String routeName;
    private String bookingId;
    private LocalDate checkinDate;
}
