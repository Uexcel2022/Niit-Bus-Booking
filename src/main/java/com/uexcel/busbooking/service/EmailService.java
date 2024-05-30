package com.uexcel.busbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toAddress, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iakinyele3@gmail.com");
        message.setTo(toAddress);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

    }

    public void verifyEmail(String email) {
        sendEmail(
            email,
    "Rapid Transit Email Verification",
    "Kindly enter the OTP to verify your account: " + generateOTP()
        );
    }

    String generateOTP () {
//        StringBuilder otp = new StringBuilder("");
        StringBuilder otp = new StringBuilder();
        while (otp.length() < 7) {
            otp.append(new Random().nextInt(100000));
        }
        return otp.substring(0, 6);
    }

}
