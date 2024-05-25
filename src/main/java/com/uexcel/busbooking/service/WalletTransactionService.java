package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.repository.WallTransactionRepository;

import java.util.List;

public interface WalletTransactionService {
    WallTransactionRepository getUserWalletRepository();

   List<WalletTransaction> findWalletTransByWalletId(String id);
}
