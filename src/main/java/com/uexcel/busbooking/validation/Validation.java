package com.uexcel.busbooking.validation;

public class Validation {
    public  static boolean checkNullBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean checkName(String name){

        if (name == null || name.trim().isEmpty()){
            return false;
        }
        if(!name.matches("[a-zA-Z]+")){
            return false;
        }
        return name.trim().length() >= 2;
    }

    public static boolean checkEmail(String email){
        if (email == null || email.trim().isEmpty()){
            return false;
        }
        return email.matches("[a-z]+\\.?[a-z0-9_]*@[a-z0-9]+\\.?[a-z0-9]+\\.[a-z]{2,3}");
    }

    public static boolean checkPhone(String phone){
        if (phone == null || phone.trim().isEmpty()){
            return true;
        }
        return !phone.matches("(\\+234|0)[7-9][01][0-9]{8}");
    }

    public static boolean checkPassword(String password){
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

    }
