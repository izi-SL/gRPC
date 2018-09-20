package com.isuru.wallet.dao;

import com.isuru.wallet.domain.Account;

import java.util.List;

/**
 * Handle @{@link Account} data access
 *
 * @author Isuru Gajanayake
 */
public interface AccountDAO {

    /**
     * Return user's accounts with currency types
     *
     * @param userId
     * @return
     */
    public List<Account> getUserAccounts(final int userId);
}
