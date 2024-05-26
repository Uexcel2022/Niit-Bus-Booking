package com.uexcel.busbooking.dto;

import lombok.Data;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

@Data
public class BusCheckinQueryDto {
    private String busCode;
    private String busCurrentRouteId;
    private LocalDate date;
}
