package com.isuru.wallet.concurrent.event;

import com.isuru.wallet.client.api.WalletServiceAPI;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.DepositRequest;
import com.isuru.wallet.service.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Event for make deposit request
 *
 * @author Isuru Gajanayake
 */
public class DepositEvent implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(DepositEvent.class);

    private double amount;
    private CurrencyType currencyType;
    private WalletServiceAPI api;
    private int userId;

    public DepositEvent(final int userId, final double amount, final CurrencyType currencyType,
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
        api.createDeposit(DepositRequest.newBuilder()
                .setDeposit(transaction)
                .build());
        LOGGER.info(Thread.currentThread().getName() +  " triggered DepositEvent");
    }


}
