package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.entity.UserWallet;
import com.uexcel.busbooking.repository.UserWalletRepository;
public interface UserWalletService {
    UserWalletRepository getUserWalletRepository();

    ResponseDto processWalletFunding(WalletFundingDto walletFundingDto);

    UserWallet findWalletByUserId(String userId);
}
