package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.BankCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCodeRespository extends JpaRepository<BankCode, String> {
}
