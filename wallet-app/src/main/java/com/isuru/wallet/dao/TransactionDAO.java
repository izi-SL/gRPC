package com.isuru.wallet.dao;

import com.isuru.wallet.service.CurrencyType;

/**
 * Handle @{@link com.isuru.wallet.domain.TransactionHistory} data access
 *
 * @author Isuru Gajanayake
 */
public interface TransactionDAO {

    /**
     * Make a deposit
     *
     * @param userId       @int
     * @param amount       @double
     * @param currencyType @{@link CurrencyType}
     * @return @{@link String}
     */
    public String createDeposit(final int userId, final double amount, final CurrencyType currencyType);

    /**
     * Make a withdrawal
     *
     * @param userId       @int
     * @param amount       @double
     * @param currencyType @{@link CurrencyType}
     * @return @{@link String}
     */
    public String createWithdrawal(final int userId, final double amount, final CurrencyType currencyType);
}
