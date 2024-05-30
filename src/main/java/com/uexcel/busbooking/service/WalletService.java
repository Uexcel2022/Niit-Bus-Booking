package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.WalletFundingDto;
import com.uexcel.busbooking.dto.WalletInfoDto;
import com.uexcel.busbooking.dto.WalletTransactionInfoDto;

import java.util.List;
import java.util.Map;

public interface WalletService {

    String processWalletFunding(WalletFundingDto walletFundingDto);

    WalletInfoDto findWalletByWalletNumber(String userId);

    List<WalletTransactionInfoDto> findWalletTransByWalletNumber(String id);


    List<WalletTransactionInfoDto> findAllClientWallet();

    String walletTransfer(Map<String, String> walletTransferData);
}
