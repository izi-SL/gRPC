package com.isuru.wallet.concurrent;

import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.Transaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Isuru Gajanayake
 */
public class DataSimulator {

    private Map<Integer, Round> events = new ConcurrentHashMap<>();

    public static Map<Integer, Round> simulateData() {
        final Event checkBalance = new Event(EventType.CHECK_BALANCE, null);
        final Event deposit100USD = new Event(EventType.DEPOSIT, getTransaction(1, 100.00, CurrencyType.USD));
        final Event withdraw200USD = new Event(EventType.WITHDRAW, getTransaction(1, 200.00, CurrencyType.USD));
        final Event withdraw100USD = new Event(EventType.WITHDRAW, getTransaction(1, 100.00, CurrencyType.USD));

    }

    private static Transaction getTransaction(final int userId, final double amount, final CurrencyType currencyType) {
        return Transaction.newBuilder()
                .setUserId(userId)
                .setCurrency(currencyType)
                .setAmount(amount)
                .build();
    }

}
