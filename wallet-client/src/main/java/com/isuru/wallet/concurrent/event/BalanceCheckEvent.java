package com.isuru.wallet.concurrent.event;

import com.isuru.wallet.client.api.WalletServiceAPI;
import com.isuru.wallet.service.BalanceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Event for check account balance
 *
 * @author Isuru Gajanayake
 */
public class BalanceCheckEvent implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(DepositEvent.class);

    private WalletServiceAPI api;
    private int userId;

    public BalanceCheckEvent(final int userId, final WalletServiceAPI api) {
        this.api = api;
        this.userId = userId;
    }

    @Override
    public void run() {
        api.getAccoutBalances(BalanceRequest.newBuilder()
                .setUserId(this.userId)
                .build());
        LOGGER.info(Thread.currentThread().getName() +  " triggered BalanceCheckEvent");
    }
}
