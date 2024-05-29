package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;
import com.uexcel.busbooking.entity.ClientWallet;

import java.util.List;

public interface WalletService {

    ResponseDto processWalletFunding(WalletFundingDto walletFundingDto);

    ClientWallet findWalletByWalletNumber(String userId);

    List<WalletTransactionInfoDto> findWalletTransByWalletNumber(String id);



}
