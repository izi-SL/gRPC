package com.isuru.wallet.concurrent;

import java.util.List;
import java.util.concurrent.*;

/**
 * Simulated user for perform API calls
 *
 * @author Isuru Gajanayake
 */
public class User implements Runnable {

    private List<Round> rounds;
    private BlockingQueue<Runnable> worksQueue;
    private RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandelerImpl();
    private ThreadPoolExecutor executor;

    public User(final List<Round> rounds) {
        this.worksQueue = new ArrayBlockingQueue<>(rounds.size());
        this.executor = new ThreadPoolExecutor(rounds.size(), rounds.size(), 10, TimeUnit.SECONDS,
                worksQueue, rejectionHandler);
        this.rounds = rounds;
    }

    @Override
    public void run() {
        executor.prestartAllCoreThreads();
        worksQueue.addAll(rounds);
    }
}


/**
 * Handler for thread execration rejection
 */
class RejectedExecutionHandelerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable runnable,
                                  ThreadPoolExecutor executor) {
        System.out.println(new StringBuilder().append(runnable.toString()).append(" -> rejected ! ").toString());
    }
}
