package com.isuru.wallet.dao.impl;

import com.isuru.wallet.dao.TransactionDAO;
import com.isuru.wallet.domain.Account;
import com.isuru.wallet.domain.TransactionHistory;
import com.isuru.wallet.domain.User;
import com.isuru.wallet.exception.UnknownAccountException;
import com.isuru.wallet.repo.config.HibernateUtil;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.service.Transaction;
import com.isuru.wallet.status.message.ResponseMessage;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class TransactionDAOImpl implements TransactionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDAOImpl.class);

    /**
     * Make a deposit
     *
     * @param userId       @int
     * @param amount       @double
     * @param currencyType @{@link com.isuru.wallet.service.CurrencyType}
     */
    @Override
    public String createDeposit(final int userId, final double amount, final CurrencyType currencyType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = this.getUserByUserId(session, userId);

            if (null != user) {
                Account account = this.getUserAccountByCurrencyType(session, user, currencyType);
                if (null == account) {
                    account = new Account();
                    account.setDefaultCurrencyType(currencyType);
                    account.setBalance(amount);
                    account.setUser(user);
                    session.save(account);
                }

                account.setBalance(account.getBalance() + amount);

                TransactionHistory transaction = this.createTransaction(account, amount, Transaction.TransactionType.DEPOSIT);
                try {
                    account.setVersion(this.getAccountByAccountId(session, account.getId()).getVersion());
                    session.save(transaction);
                    session.getTransaction().commit();
                } catch (OptimisticLockException oex) {
                    LOGGER.error(new StringBuilder().append("Cannot give the priority to this transaction ").append(oex.toString()).toString());
                }
                return ResponseMessage.TRANSACTION_SUCCESS;
            } else {
                return ResponseMessage.UNKNOWN_USER;
            }

        } catch (Exception ex) {
            if (null != session) {
                LOGGER.info("Transaction is being rolled back");
                session.getTransaction().rollback();
                LOGGER.error("Exception :", ex);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * Make a withdrawal
     *
     * @param userId       @int
     * @param amount       @double
     * @param currencyType @{@link com.isuru.wallet.service.CurrencyType}
     */
    @Override
    public String createWithdrawal(final int userId, final double amount, final CurrencyType currencyType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = this.getUserByUserId(session, userId);
            if (null != user) {
                Account account = this.getUserAccountByCurrencyType(session, user, currencyType);
                if (null == account) {
                    throw new UnknownAccountException(new StringBuilder().append("This user id ").append(userId).append("does not have account with").append(currencyType.name()).append(" currency. ").toString());
                }

                if (this.isValidWithdrawal(account, amount)) {
                    final TransactionHistory transaction = this.createTransaction(account, amount, Transaction.TransactionType.WITHDRAWAL);
                    account.setBalance(account.getBalance() - amount);
                    session.save(transaction);
                    try {
                        account.setVersion(this.getAccountByAccountId(session, account.getId()).getVersion());
                        session.save(transaction);
                        session.getTransaction().commit();
                    } catch (OptimisticLockException oex) {
                        LOGGER.error(new StringBuilder().append("Cannot give the priority to this transaction ").append(oex.toString()).toString());
                    }
                    return ResponseMessage.TRANSACTION_SUCCESS;
                } else {
                    return ResponseMessage.INSUFFICIENT_FUND;
                    // throw new InsufficientFundException(new StringBuilder().append("Account ").append(account.getId()).append("does not have sufficient funds for complete this withdrawal").toString());
                }

            } else {
                return ResponseMessage.UNKNOWN_USER;
            }
        } catch (Exception ex) {
            if (null != session) {
                LOGGER.info("Transaction is being rolled back");
                session.getTransaction().rollback();
                LOGGER.error("Exception :", ex);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }


    /**
     * Return user account by given currency type
     *
     * @param session      @{@link Session}
     * @param user         @{@link User}
     * @param currencyType @{@link com.isuru.wallet.service.CurrencyType}
     * @return @{@link Account}
     */
    private Account getUserAccountByCurrencyType(final Session session, final User user, final CurrencyType currencyType) {
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Account> cr = cb.createQuery(Account.class);
            Root<Account> root = cr.from(Account.class);

            final Predicate[] predicates = new Predicate[2];
            predicates[0] = cb.equal(root.get("user"), user);
            predicates[1] = cb.equal(root.get("defaultCurrencyType"), currencyType);

            final Query query = session.createQuery(cr.select(root).where(cb.and(predicates[0], predicates[1])));
            final Account account = (Account) query.getSingleResult();
            return account;
        } catch (NoResultException nre) {
            LOGGER.info(new StringBuilder().append("No result found for User Id: ").append(user.getId()).append(" Currency Type:").append(currencyType).toString());
            return null;
        }
    }


    /**
     * Return user by user id
     *
     * @param session @{@link Session}
     * @param userId  @int
     * @return @{@link User}
     */
    private User getUserByUserId(final Session session, final int userId) {
        return session.get(User.class, userId);
    }

    /**
     * Return account by id
     *
     * @param session   @{@link Session}
     * @param accountId @int
     * @return @{@link Account}
     */
    private Account getAccountByAccountId(final Session session, final int accountId) {
        return session.get(Account.class, accountId);
    }

    /**
     * Create @{@link TransactionHistory} record
     *
     * @param account         @{@link Account}
     * @param amount          @double
     * @param transactionType @{@link com.isuru.wallet.service.Transaction.TransactionType}
     * @return @{@link com.isuru.wallet.service.Transaction.TransactionType}
     */
    private TransactionHistory createTransaction(final Account account,
                                                 final double amount,
                                                 final Transaction.TransactionType transactionType) {
        TransactionHistory transaction = new TransactionHistory();
        transaction.setId(0);
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setCurrencyType(account.getDefaultCurrencyType());
        transaction.setTransactionType(transactionType);
        return transaction;
    }

    /**
     * Validate the cash withdrawal
     *
     * @param account @{@link Account}
     * @param amount  @double
     * @return @boolean
     */
    private boolean isValidWithdrawal(final Account account, final double amount) {
        return account.getBalance() >= amount ? true : false;
    }


}
