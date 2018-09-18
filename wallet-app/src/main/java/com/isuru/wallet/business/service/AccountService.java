package com.isuru.wallet.business.service;

import com.isuru.wallet.service.BalanceRequest;
import com.isuru.wallet.service.BalanceResponse;

/**
 * Definition of account operations
 *
 * @author Isuru Gajanayake
 */
public interface AccountService {

    /**
     * Check account balances of all accounts of user
     * @param balanceRequest @{@link BalanceRequest}
     * @return @{@link BalanceResponse}
     */
    public BalanceResponse checkAccountsBalance(final BalanceRequest balanceRequest);
}
