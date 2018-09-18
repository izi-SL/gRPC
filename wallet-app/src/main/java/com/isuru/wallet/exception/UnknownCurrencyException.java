package com.isuru.wallet.exception;

/**
 * Exception to indicate that user input invalid currency format
 *
 * @author Isuru Gajanayake
 */
public class UnknownCurrencyException extends RuntimeException {

    public UnknownCurrencyException(final String message) {
        super(message);
    }
}
