package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.repository.ClientWalletRepository;
public interface ClientWalletService {
    ClientWalletRepository getUserWalletRepository();

    ResponseDto processWalletFunding(WalletFundingDto walletFundingDto);

    ClientWallet findWalletByUserId(String userId);
}
