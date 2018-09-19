package com.isuru.wallet.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Set of Events
 */
public class Round implements Runnable {

    private final List<Runnable> events;
    private ExecutorService executorService;
    private int taskThreads;

    public Round(final List<Runnable> events, final int taskThreads) {
        this.events = events;
        this.taskThreads = taskThreads;
    }

    @Override
    public void run() {
        this.executorService = Executors.newFixedThreadPool(taskThreads);
        for (Runnable event : events) {
            executorService.execute(event);
        }
        executorService.shutdown();
    }
}
