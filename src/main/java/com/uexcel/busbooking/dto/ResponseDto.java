package com.uexcel.busbooking.dto;

import lombok.Data;

@Data
public class ResponseDto {
    String response;
   public ResponseDto (String response) {
       this.response = response;
   }
   public ResponseDto(){}
}
