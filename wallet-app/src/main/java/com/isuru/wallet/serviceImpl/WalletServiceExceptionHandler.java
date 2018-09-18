package com.isuru.wallet.serviceImpl;

import com.isuru.wallet.status.message.ResponseMessage;

/**
 * Handle All the exception messages send to client from wallet service
 *
 * @author Isuru Gajanayake
 */
public class WalletServiceExceptionHandler {

    public static String getExceptionMessage(final String status) {
        switch (status) {
            case ResponseMessage.UNKNOWN_USER:
                return "User is not found in the system, Please do the user registration.";
            case ResponseMessage.UNKNOWN_CURRENCY:
                return "Currency is not recognize, use only EUR, USD, GPB";
            case ResponseMessage.INSUFFICIENT_FUND:
                return "Account fund is not sufficient to do the transaction.";
            default:
                return "Internal System error: unable to specify.";
        }

    }
}
