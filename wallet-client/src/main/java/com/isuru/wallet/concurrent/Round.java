package com.isuru.wallet.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Set of Events
 */
public class Round implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Round.class);

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
            LOGGER.info(Thread.currentThread().getName() + "  Round triggered");
            executorService.execute(event);
        }
        executorService.shutdown();
    }
}
