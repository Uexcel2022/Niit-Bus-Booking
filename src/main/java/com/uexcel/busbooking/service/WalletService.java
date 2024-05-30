package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletInfoDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;

import java.util.List;

public interface WalletService {

    ResponseDto processWalletFunding(WalletFundingDto walletFundingDto);

    WalletInfoDto findWalletByWalletNumber(String userId);

    List<WalletTransactionInfoDto> findWalletTransByWalletNumber(String id);


    List<WalletTransactionInfoDto> findClientWallet();


}
