package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet,Long> {
    @Query(value = "SELECT p FROM UserWallet p WHERE p.walletCode=:walletCode")
    UserWallet findByWalletCode(String walletCode);
}
