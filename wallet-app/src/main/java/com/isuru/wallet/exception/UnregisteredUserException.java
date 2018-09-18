package com.isuru.wallet.exception;

/**
 * Exception to indicate that user not registered with the system
 *
 * @author Isuru Gajanayake
 */
public class UnregisteredUserException extends RuntimeException {

    public UnregisteredUserException(final String message) {
        super(message);
    }
}
