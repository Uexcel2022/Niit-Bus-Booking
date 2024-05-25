package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet,String> {
//    @Query(value = "SELECT p FROM UserWallet p WHERE p.walletCode=:walletCode")
//    UserWallet findByWalletCode(String walletCode);

    @Query(nativeQuery = true,value = "SELECT * FROM user_wallet WHERE user_id=:userId")
    UserWallet findUserWalletByUserId(String userId);

}
