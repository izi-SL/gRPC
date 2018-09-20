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
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Wallet-client implementation for simulate user API calls
 *
 * @author Isuru Gajanayake
 */
public class WalletClient {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletClient.class);

    private static WalletServiceAPI api = new WalletServiceAPI();
    private static List<Round> allRounds;

    public static void main(String[] args) {

        final int users = Integer.parseInt(args[0]);
        final int concurrent_threads_per_user = Integer.parseInt(args[1]);
        final int rounds_per_thread = Integer.parseInt(args[2]);

        LOGGER.info("Users :"+ users+ " concurrent_threads_per_user :"+ concurrent_threads_per_user+ " rounds_per_thread :"+ rounds_per_thread);

        /**
         * Generate user data for operations
         */
        generateData(concurrent_threads_per_user);

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(users);

            for (int i = 0; i < users; i++) {
                List<Round> userRounds = new ArrayList<>();
                for (int j = 0; j < rounds_per_thread; j++) {
                    userRounds.add(pickRandomRound());
                }
                User user = new User(allRounds);
                executorService.execute(user);
            }
            executorService.shutdown();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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

    /**
     * Pick round by random
     *
     * @return {@link Round}
     */
    private static Round pickRandomRound() {
        Random random = new Random();
        return allRounds.get(random.nextInt(allRounds.size()));
    }
}
