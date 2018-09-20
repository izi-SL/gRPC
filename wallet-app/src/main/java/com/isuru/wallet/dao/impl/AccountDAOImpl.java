package com.isuru.wallet.dao.impl;

import com.isuru.wallet.dao.AccountDAO;
import com.isuru.wallet.domain.Account;
import com.isuru.wallet.domain.User;
import com.isuru.wallet.repo.config.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDAOImpl.class);

    /**
     * Return user's accounts with currency types
     *
     * @param userId @int
     * @return @{@link List<Account>}
     */
    @Override
    public List<Account> getUserAccounts(final int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userId);

        if (user != null) {
            List<Account> accounts = this.getAccountById(session, user);
            return accounts;
        } else {
            throw new UnregisteredUserException("User is not found in the system, Please do the user registration. ");
        }
    }


    /**
     * Return user's accounts by user id
     *
     * @param session @{@link Session}
     * @param user    {@link User}
     * @return @{@link List<Account>}
     */
    private List<Account> getAccountById(final Session session, final User user) {
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Account> cr = cb.createQuery(Account.class);
            Root<Account> root = cr.from(Account.class);

            final Query query = session.createQuery(cr.select(root).where(cb.equal(root.get("user"), user)));
            final List<Account> accounts = query.getResultList();
            return accounts;
        } catch (NoResultException nre) {
            LOGGER.info(new StringBuilder().append("No account found for User Id: ").append(user.getId()).toString());
            return null;
        }
    }
}
