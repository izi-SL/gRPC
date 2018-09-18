package com.isuru.wallet.repo.config;

import com.isuru.wallet.domain.Account;
import com.isuru.wallet.domain.TransactionHistory;
import com.isuru.wallet.domain.User;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate database according to the schema before app start
 *
 * @author Isuru Gajanayake
 */
public class WalletDatabaseGenerator {

    public static void build() {
        final Map<String, String> settings = new HashMap<>();

        settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
        settings.put("connection.pool_size", "10");
        settings.put("dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
        settings.put("hibernate.connection.url", "jdbc:mysql://localhost/walletDb?useSSL=false");
        settings.put("hibernate.connection.username", "wallet_admin");
        settings.put("hibernate.connection.password", "admin");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("show_sql", "true");

        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build()
        );
        metadata.addAnnotatedClass(Account.class);
        metadata.addAnnotatedClass(TransactionHistory.class);
        metadata.addAnnotatedClass(User.class);

        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile("db-schema.sql");
        schemaExport.execute(EnumSet.of(TargetType.DATABASE), SchemaExport.Action.BOTH, (Metadata) metadata.buildMetadata());

    }
}
