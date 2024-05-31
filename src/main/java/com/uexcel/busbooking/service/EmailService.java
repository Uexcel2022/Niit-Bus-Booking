package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.EmailDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.entity.EmailVerification;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.repository.ClientRepository;
import com.uexcel.busbooking.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void sendEmail(String toAddress, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iakinyele3@gmail.com");
        message.setTo(toAddress);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

    }

    public void requestEmailOTP(String email) {
        String otp = generateOTP();
        sendEmail(
            email,
    "Rapid Transit Email Verification",
    "Kindly enter the OTP to verify your account: " + otp
        );
        emailRepository.deleteByEmail(email);
        EmailVerification emailVerification = EmailVerification
                .builder()
                        .email(email)
                        .otp(otp)
                        .build();
        emailRepository.save(emailVerification);
    }

    public ResponseEntity<String> verifyEmail(EmailDto emailDto) {
        EmailVerification emailVerification = emailRepository.findByEmailIgnoreCase(emailDto.getEmail());
        if (emailVerification != null) {
            if (emailDto.getOtp().equals(emailVerification.getOtp())) {
                // TODO: 5/31/2024 - update email field in client entity
                Client client = clientRepository.findByEmail(emailDto.getEmail());
                if(client == null) {
                    throw new CustomException("User not found", "404");
                }
                client.setEmailVerified(true);
                clientRepository.save(client);
                emailRepository.deleteByEmail(emailDto.getEmail());
                return ResponseEntity.ok().body("Email verified successfully");
            } else {
                throw new CustomException("Invalid OTP", "400");
            }
        } else {
            throw new CustomException("Email not found", "404");
        }

    }

    String generateOTP () {
        StringBuilder otp = new StringBuilder();
        while (otp.length() < 7) {
            otp.append(new Random().nextInt(100000));
        }
        return otp.substring(0, 6);
    }

}
