package com.uexcel.busbooking.utils;

import org.springframework.stereotype.Component;

@Component
public class Validation {
    public  boolean checkNullBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public boolean checkName(String name){

        return !name.matches("[a-zA-Z]{2,}\s[A-Za-z]{2,}\s?[A-Za-z]*");
    }

    public static boolean checkDaDate(String date){
        return !date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public  boolean checkEmail(String email){

        return !email.matches("[a-z]+\\.?[a-z0-9_]*@[a-z0-9]+\\.?[a-z0-9]+\\.[a-z]{2,3}");
    }

    public boolean checkPhone(String phone){

        return !phone.matches("(\\+234|0)[7-9][01][0-9]{8}");
    }

    public boolean checkPassword(String password){
        if (password == null || password.trim().isEmpty() || password.length() < 6  || password.length() > 16){
            return true;
        }
         char[] chars = password.toCharArray();
        String uppCase ="[A-Z]";
        String specialChr = "[!$#&?_+-]";
        String numbers = "[0-9]";
        boolean matchedUppCase = false;
        boolean matchedSpecialChr = false;
        boolean matchedNumbers = false;

        for (char aChar : chars) {
            if (String.valueOf(aChar).matches(uppCase)) {
                matchedUppCase = true;
                break;
            }
        }
        if(!matchedUppCase){
            return true;
        }

        for (char aChar : chars) {
            if (String.valueOf(aChar).matches(specialChr)) {
                matchedSpecialChr = true;
                break;
            }
        } if(!matchedSpecialChr){
            return true;
        }

        for (char aChar : chars) {
            if (String.valueOf(aChar).matches(numbers)) {
                matchedNumbers = true;
                break;
            }
        }

        return !matchedNumbers;
    }
    public boolean checkAccountNumber(String accountNumber){
        return !accountNumber.matches("[0-9]{10}");
    }

    }
