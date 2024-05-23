package com.uexcel.busbooking.repository;


import com.uexcel.busbooking.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WallTransactionRepository extends JpaRepository<WalletTransaction,Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM ")
  List<WalletTransaction> findByWalletId(Long userId);
}
