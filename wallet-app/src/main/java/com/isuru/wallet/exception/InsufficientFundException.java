package com.isuru.wallet.exception;

/**
 * Exception to indicate that user's particular account does not have sufficient funds to do the transaction
 *
 * @author Isuru Gajanayake
 */
public class InsufficientFundException extends RuntimeException {

    public InsufficientFundException(final String message) {
        super(message);
    }
}
