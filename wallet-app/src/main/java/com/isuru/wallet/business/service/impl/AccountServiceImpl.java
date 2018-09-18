package com.isuru.wallet.business.service.impl;

import com.isuru.wallet.business.service.AccountService;
import com.isuru.wallet.dao.AccountDAO;
import com.isuru.wallet.dao.impl.AccountDAOImpl;
import com.isuru.wallet.domain.Account;
import com.isuru.wallet.service.AccountBalance;
import com.isuru.wallet.service.BalanceRequest;
import com.isuru.wallet.service.BalanceResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @see com.isuru.wallet.business.service.AccountService
 */
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO = new AccountDAOImpl();

    /**
     * Check account balances of all accounts of user
     *
     * @param balanceRequest @{@link BalanceRequest}
     * @return @{@link BalanceResponse}
     */
    @Override
    public BalanceResponse checkAccountsBalance(final BalanceRequest balanceRequest) {
        final List<Account> accounts = this.accountDAO.getUserAccounts(balanceRequest.getUserId());

        final List<AccountBalance> accountBalances = accounts.stream().map(account -> {
            return AccountBalance.newBuilder()
                    .setUserId(account.getUser().getId())
                    .setAccountId(account.getId())
                    .setUserEmail(account.getUser().getEmail())
                    .setBalance(account.getBalance())
                    .setCurrency(account.getDefaultCurrencyType())
                    .build();

        }).collect(Collectors.toList());

        final BalanceResponse balanceResponse = BalanceResponse.newBuilder()
                .addAllAccountBalance(accountBalances)
                .build();

        return balanceResponse;
    }
}
