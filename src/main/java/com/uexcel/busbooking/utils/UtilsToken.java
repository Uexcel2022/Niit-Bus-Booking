package com.uexcel.busbooking.utils;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.LoginDto;
import com.uexcel.busbooking.exception.CustomException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

@Component
public class UtilsToken {
    public void checkPhoneNumber(String phone) {
        boolean valid = Pattern.matches("(\\+?234|0)[7-9][01]\\d{8}", phone);
        if (!valid) {
            throw new CustomException("Invalid nigerian phone number");
        }
    }
    public boolean validateEmail(String email) {
        return Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email);
    }
    public boolean validateEmailFromToken(String token) throws Exception {
        String email = retrieveEmailFromToken(token);
        return validateEmail(email);
    }
    public String retrieveEmailFromToken(String token) throws Exception {
        return decode(token).split(":::")[0];
    }
    public String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    public String decode(String token) throws Exception {
        try {
            return new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("Invalid Token");
        }
    }
}
