package com.isuru.wallet.client;

import com.isuru.wallet.client.api.WalletServiceAPI;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.DepositRequest;
import com.isuru.wallet.service.Transaction;
import com.isuru.wallet.service.WithdrawalRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Wallet-client implementation
 *
 * @author Isuru Gajanayake
 */
public class WalletClient {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletClient.class);

    private final static WalletServiceAPI WALLET_SERVICE_API = new WalletServiceAPI();

    public static void main(String[] args) {

        final int users = 1;
        final int concurrent_threads_per_user = 2;
        final int rounds_per_thread = 2;

        Transaction transaction = Transaction.newBuilder()
                .setAmount(20.0)
                .setCurrency(CurrencyType.EUR)
                .setUserId(1)
                .build();
        Transaction transaction2 = Transaction.newBuilder()
                .setAmount(200.0)
                .setCurrency(CurrencyType.EUR)
                .setUserId(2)
                .build();
        final DepositRequest depositRequest = DepositRequest.newBuilder()
                .setDeposit(transaction)
                .build();
        final WithdrawalRequest withdrawalRequest = WithdrawalRequest.newBuilder()
                .setWithdrawal(transaction)
                .build();
        final DepositRequest depositRequest2 = DepositRequest.newBuilder()
                .setDeposit(transaction2)
                .build();
        final WithdrawalRequest withdrawalRequest2 = WithdrawalRequest.newBuilder()
                .setWithdrawal(transaction2)
                .build();



        try {

            ExecutorService executorService = Executors.newFixedThreadPool(concurrent_threads_per_user);

            executorService.execute(() -> {
                for (int i = 0; i < 1; i++) {
                    System.out.println("Ex 1");
                    WALLET_SERVICE_API.createDeposit(depositRequest);
                    WALLET_SERVICE_API.createDeposit(depositRequest2);
                }
            });

            executorService.execute(() -> {
                for (int i = 0; i < 1; i++) {
                    System.out.println("Ex 2");
                    WALLET_SERVICE_API.createWithdrawal(withdrawalRequest);
                    WALLET_SERVICE_API.createWithdrawal(withdrawalRequest2);
                }
            });

            executorService.shutdown();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


    }
}
