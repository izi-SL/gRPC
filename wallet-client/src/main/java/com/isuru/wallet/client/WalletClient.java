package com.isuru.wallet.client;

import com.isuru.wallet.client.api.WalletServiceAPI;
import com.isuru.wallet.concurrent.Round;
import com.isuru.wallet.concurrent.User;
import com.isuru.wallet.concurrent.event.BalanceCheckEvent;
import com.isuru.wallet.concurrent.event.DepositEvent;
import com.isuru.wallet.concurrent.event.WithdrawEvent;
import com.isuru.wallet.service.CurrencyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Wallet-client implementation
 *
 * @author Isuru Gajanayake
 */
public class WalletClient {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletClient.class);

    private static WalletServiceAPI api = new WalletServiceAPI();
    private static List<Round> allRounds;

    public static void main(String[] args) {

        final int users = 1;
        final int concurrent_threads_per_user = 2;
        final int rounds_per_thread = 2;

        /**
         * Generate dummy data
         */
        generateData(concurrent_threads_per_user);

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(users);
            allRounds.remove(2);

            for (int i = 0; i < users; i++) {
                User user = new User(allRounds);
                executorService.execute(user);
            }
            executorService.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


    }


    /**
     * Generate user data
     */
    private static void generateData(final int concurrentThreadsPerUser) {
        allRounds = new ArrayList<>();
        // user 1
        DepositEvent depositEvent100USD = new DepositEvent(1, 100.0, CurrencyType.USD, api);
        DepositEvent depositEvent100EUR = new DepositEvent(1, 100.0, CurrencyType.EUR, api);
        DepositEvent depositEvent300GBP = new DepositEvent(1, 100.0, CurrencyType.GBP, api);
        WithdrawEvent withdrawEvent100USD = new WithdrawEvent(1, 200, CurrencyType.USD, api);
        WithdrawEvent withdrawEvent200USD = new WithdrawEvent(1, 100, CurrencyType.USD, api);
        WithdrawEvent withdrawEvent100GBP = new WithdrawEvent(1, 100, CurrencyType.GBP, api);
        BalanceCheckEvent balanceCheckEvent = new BalanceCheckEvent(1, api);

        List<Runnable> eventsA = new ArrayList<>();
        eventsA.add(depositEvent100USD);
        eventsA.add(withdrawEvent200USD);
        eventsA.add(balanceCheckEvent);
        eventsA.add(withdrawEvent100USD);

        List<Runnable> eventsB = new ArrayList<>();
        eventsB.add(withdrawEvent100GBP);
        eventsB.add(depositEvent300GBP);
        eventsB.add(withdrawEvent100GBP);
        eventsB.add(withdrawEvent100GBP);
        eventsB.add(withdrawEvent100GBP);

        List<Runnable> eventsC = new ArrayList<>();
        eventsC.add(balanceCheckEvent);
        eventsC.add(depositEvent100USD);
        eventsC.add(depositEvent100USD);
        eventsC.add(withdrawEvent100USD);
        eventsC.add(depositEvent100USD);
        eventsC.add(balanceCheckEvent);
        eventsC.add(withdrawEvent200USD);
        eventsC.add(balanceCheckEvent);

        Round roundA = new Round(eventsA, concurrentThreadsPerUser);
        Round roundB = new Round(eventsB, concurrentThreadsPerUser);
        Round roundC = new Round(eventsC, concurrentThreadsPerUser);

        allRounds.add(roundA);
        allRounds.add(roundB);
        allRounds.add(roundC);


    }
}
