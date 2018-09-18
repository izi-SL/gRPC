package com.isuru.wallet.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class Round {
    private Map<Integer, Event> events = new ConcurrentHashMap<>();

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public void setEvents(Map<Integer, Event> events) {
        this.events = events;
    }
}

