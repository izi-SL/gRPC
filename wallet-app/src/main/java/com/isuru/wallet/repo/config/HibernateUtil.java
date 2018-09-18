package com.isuru.wallet.repo.config;

import com.isuru.wallet.domain.Account;
import com.isuru.wallet.domain.TransactionHistory;
import com.isuru.wallet.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Hibernate utility implementation for get @{@link SessionFactory} and destroy @{@link StandardServiceRegistry}
 */
public class HibernateUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    static {
        try {
            if (sessionFactory == null) {
                final Map<String, String> settings = new HashMap<>();

                settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
                settings.put("connection.pool_size", "10");
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
                settings.put("hibernate.connection.url", "jdbc:mysql://localhost/walletDb?useSSL=false&allowPublicKeyRetrieval=true");
                settings.put("hibernate.connection.username", "wallet_admin");
                settings.put("hibernate.connection.password", "admin");
                settings.put("show_sql", "true");
                registry = new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build();

                MetadataSources metadata = new MetadataSources(
                        new StandardServiceRegistryBuilder()
                                .applySettings(settings)
                                .build()
                );
                metadata.addAnnotatedClass(Account.class);
                metadata.addAnnotatedClass(TransactionHistory.class);
                metadata.addAnnotatedClass(User.class);

                sessionFactory = metadata.getMetadataBuilder()
                        .build()
                        .buildSessionFactory();
            }
        } catch (Throwable th) {
            LOGGER.error("Initial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }

    }

    /**
     * @return @{@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Destroy service registry
     */
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
