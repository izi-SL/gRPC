package com.isuru.wallet.business.service.impl;

import com.isuru.wallet.business.service.TransactionService;
import com.isuru.wallet.dao.AccountDAO;
import com.isuru.wallet.dao.TransactionDAO;
import com.isuru.wallet.dao.impl.AccountDAOImpl;
import com.isuru.wallet.dao.impl.TransactionDAOImpl;
import com.isuru.wallet.exception.UnknownCurrencyException;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.DepositRequest;
import com.isuru.wallet.service.Transaction;
import com.isuru.wallet.service.WithdrawalRequest;
import com.isuru.wallet.status.message.ResponseMessage;

/**
 * @see @{@link TransactionService}
 */
public class TransactionServiceImpl implements TransactionService {


    private final TransactionDAO transactionDAO = new TransactionDAOImpl();

    /**
     * Create deposit for given currency type (EUR, USD, GBP)
     *
     * @param request @{@link DepositRequest}
     */
    @Override
    public String createCashDeposit(final DepositRequest request) {
        final Transaction transaction = request.getDeposit();

        if (this.isValidCurrency(transaction)) {
            return transactionDAO.createDeposit(transaction.getUserId(), transaction.getAmount(), transaction.getCurrency());
        } else {
            return ResponseMessage.UNKNOWN_CURRENCY;
        }
    }

    /**
     * Create withdrawal for given currency type (EUR, USD, GBP)
     *
     * @param request @{@link WithdrawalRequest}
     */
    @Override
    public String createCashWithdrawal(final WithdrawalRequest request) {
        final Transaction transaction = request.getWithdrawal();

        if (this.isValidCurrency(transaction)) {
            return transactionDAO.createWithdrawal(transaction.getUserId(), transaction.getAmount(), transaction.getCurrency());
        } else {
            return ResponseMessage.UNKNOWN_CURRENCY;
        }
    }


    /**
     * Validate the currency type
     *
     * @param transaction @{@link Transaction}
     * @return @boolean
     */
    private boolean isValidCurrency(final Transaction transaction) {
        if (transaction.getCurrency().equals(CurrencyType.EUR)
                || transaction.getCurrency().equals(CurrencyType.GBP)
                || transaction.getCurrency().equals(CurrencyType.USD)) {
            return true;
        } else {
            return false;
        }

    }
}
