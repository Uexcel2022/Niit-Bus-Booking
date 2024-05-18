package com.uexcel.busbooking.dto;

import lombok.Data;

@Data
public class QueryUser {
    private int id;
    private String email;
    private String password;
}
