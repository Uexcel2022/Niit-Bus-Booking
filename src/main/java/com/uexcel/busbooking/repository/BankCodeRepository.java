package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.BankCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCodeRepository extends JpaRepository<BankCode, String> {

    BankCode findByBankCodeOrBankName(String bankCode, String bankName);
}
