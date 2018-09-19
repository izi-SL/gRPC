package com.isuru.wallet.concurrent.event;

import com.isuru.wallet.client.api.WalletServiceAPI;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.Transaction;
import com.isuru.wallet.service.WithdrawalRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Event for make withdraw request
 *
 * @author Isuru Gajanayake
 */
public class WithdrawEvent implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(WithdrawEvent.class);

    private int userId;
    private double amount;
    private CurrencyType currencyType;
    private WalletServiceAPI api;

    public WithdrawEvent(final int userId, final double amount, final CurrencyType currencyType,
                         final WalletServiceAPI api) {
        this.userId = userId;
        this.amount = amount;
        this.currencyType = currencyType;
        this.api = api;
    }

    @Override
    public void run() {
        Transaction transaction = Transaction.newBuilder()
                .setAmount(amount)
                .setCurrency(currencyType)
                .setUserId(userId)
                .build();
        api.createWithdrawal(WithdrawalRequest.newBuilder()
                .setWithdrawal(transaction)
                .build());
        LOGGER.info(Thread.currentThread().getName() +  " triggered WithdrawEvent");
    }


}
