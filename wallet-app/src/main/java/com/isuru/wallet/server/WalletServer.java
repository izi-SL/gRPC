package com.isuru.wallet.server;

import com.isuru.wallet.dao.TransactionDAO;
import com.isuru.wallet.dao.impl.TransactionDAOImpl;
import com.isuru.wallet.repo.config.HibernateUtil;
import com.isuru.wallet.repo.config.WalletDatabaseGenerator;
import com.isuru.wallet.service.CurrencyType;
import com.isuru.wallet.serviceImpl.WalletServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Wallet server boilerplate implementation for proper server boot-up & shutdown
 *
 * @author Isuru Gajanayake
 */
public class WalletServer {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        LOGGER.info("Building Wallet Database... ");
        WalletDatabaseGenerator.build();
        LOGGER.info("Successfully Built The Database ... ");

        LOGGER.info("Wallet Server Starting...!!!");
        Server server = ServerBuilder.forPort(5500)
                .addService(new WalletServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Received a Shutdown Request");
            HibernateUtil.shutdown();
            server.shutdown();
            LOGGER.info("Successfully stopped the server");
        }));
        server.awaitTermination();
    }
}
