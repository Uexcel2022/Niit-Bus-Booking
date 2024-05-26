package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.ClientWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientWalletRepository extends JpaRepository<ClientWallet,String> {
//    @Query(value = "SELECT p FROM UserWallet p WHERE p.walletCode=:walletCode")
//    UserWallet findByWalletCode(String walletCode);

    @Query(nativeQuery = true,value = "SELECT * FROM client_wallet WHERE client_id=:userId")
    ClientWallet findUserWalletByUserId(String userId);

}
