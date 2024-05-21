package com.uexcel.busbooking.repository;


import com.uexcel.busbooking.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface WallTransactionRepository extends JpaRepository<WalletTransaction,Long> {

}
