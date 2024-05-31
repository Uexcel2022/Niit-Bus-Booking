package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Auth;
import com.uexcel.busbooking.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailVerification, Long> {
    EmailVerification findByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
    EmailVerification findByEmailIgnoreCase(String email);
    Optional<EmailVerification> findByEmailIgnoreCaseAndOtp(String email, String otp);
}
