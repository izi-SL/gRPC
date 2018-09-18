package com.isuru.wallet.business.service;

import com.isuru.wallet.service.DepositRequest;
import com.isuru.wallet.service.WithdrawalRequest;

/**
 * Definition of transaction operations
 *
 * @author Isuru Gajanayake
 */
public interface TransactionService {

    /**
     * Create deposit for given currency type (EUR, USD, GBP)
     *
     * @param request @{@link DepositRequest}
     */
    public String createCashDeposit(final DepositRequest request);

    /**
     * Create withdrawal for given currency type (EUR, USD, GBP)
     */
    public String createCashWithdrawal(final WithdrawalRequest request);
}
