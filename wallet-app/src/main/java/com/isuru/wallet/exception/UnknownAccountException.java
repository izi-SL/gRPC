package com.isuru.wallet.exception;

/**
 * Exception to indicate that particular user does not have account in given currency
 *
 * @author Isuru Gajanayake
 */
public class UnknownAccountException extends Exception {

    public UnknownAccountException(final String message) {
        super(message);
    }
}
