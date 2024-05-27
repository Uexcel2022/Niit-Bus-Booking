package com.uexcel.busbooking.repository;


import com.uexcel.busbooking.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WallTransactionRepository extends JpaRepository<WalletTransaction,String> {
  @Query(nativeQuery = true, value = "SELECT * FROM wallet_transaction WHERE wallet_number=:walletNumber")
  List<WalletTransaction> findByWalletNumber(String walletNumber);
}
