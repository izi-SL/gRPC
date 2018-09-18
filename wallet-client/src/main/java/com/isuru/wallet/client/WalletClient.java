package com.isuru.wallet.client;

import com.isuru.wallet.client.api.WalletServiceAPI;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.DepositRequest;
import com.isuru.wallet.service.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wallet-client implementation
 *
 * @author Isuru Gajanayake
 */
public class WalletClient {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletClient.class);

    private final static WalletServiceAPI WALLET_SERVICE_API = new WalletServiceAPI();

    public static void main(String[] args) {

        Transaction transaction = Transaction.newBuilder()
                .setAmount(100.0)
                .setCurrency(CurrencyType.GBP)
                .setUserId(1)
                .build();
        final DepositRequest depositRequest = DepositRequest.newBuilder()
                .setDeposit(transaction)
                .build();
        try {
            WALLET_SERVICE_API.createDeposit(depositRequest);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }



    }
}
