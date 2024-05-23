package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.WalletTransaction;
import com.uexcel.busbooking.repository.WallTransactionRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class WalletTransactionServiceImp implements WalletTransactionService {
    private final WallTransactionRepository wallTransactionRepository;
    public WalletTransactionServiceImp(WallTransactionRepository wallTransactionRepository) {
        this.wallTransactionRepository = wallTransactionRepository;
    }
    @Override
    public WallTransactionRepository getUserWalletRepository() {
        return wallTransactionRepository;
    }

    @Override
    public List<WalletTransaction> findWalletTransByWalletId(Long walletId) {
        return wallTransactionRepository.findByWalletId(walletId);
    }


}
