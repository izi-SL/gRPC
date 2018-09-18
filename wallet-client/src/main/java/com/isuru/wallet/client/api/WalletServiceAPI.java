package com.isuru.wallet.client.api;

import com.isuru.wallet.service.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

/**
 * Make Remote call for wallet app
 *
 * @author Isuru Gajanayake
 */
public class WalletServiceAPI {

    private final ManagedChannel CHANNEL;

    public WalletServiceAPI() {
        CHANNEL = ManagedChannelBuilder.forAddress("localhost", 5500)
                .usePlaintext()
                .build();
    }

    /**
     * Create a {@link DepositRequest} for given user with given @{@link com.isuru.wallet.service.CurrencyType}
     *
     * @param request @{@link DepositRequest}
     */
    public TransactionResponse createDeposit(final DepositRequest request) {
        final WalletServiceGrpc.WalletServiceBlockingStub remoteClient = WalletServiceGrpc.newBlockingStub(CHANNEL);
        return remoteClient.deposit(request);
    }

    /**
     * Create a @{@link WithdrawalRequest} for given user with given @{@link com.isuru.wallet.service.CurrencyType}
     *
     * @param request @{@link WithdrawalRequest}
     */
    public TransactionResponse createWithdrawal(final WithdrawalRequest request) {
        final WalletServiceGrpc.WalletServiceBlockingStub remoteClient = WalletServiceGrpc.newBlockingStub(CHANNEL);
        return remoteClient.withdraw(request);
    }

    /**
     * Return account balances by @{@link com.isuru.wallet.service.CurrencyType}
     *
     * @param request @{@link BalanceRequest}
     */
    public List<AccountBalance> getAccoutBalances(final BalanceRequest request) {
        final WalletServiceGrpc.WalletServiceBlockingStub remoteClient = WalletServiceGrpc.newBlockingStub(CHANNEL);
        final BalanceResponse response = remoteClient.getBalance(request);
        return response.getAccountBalanceList();
    }
}
