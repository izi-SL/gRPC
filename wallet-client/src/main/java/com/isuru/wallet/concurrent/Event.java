package com.isuru.wallet.concurrent;

import com.isuru.wallet.service.Transaction;

/**
 *
 */
public class Event {
    private EventType eventType;
    private Transaction transaction;

    public Event(final EventType eventType, final Transaction transaction) {
        this.eventType = eventType;
        this.transaction = transaction;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Transaction getTransaction() {
        return transaction;
    }

}
