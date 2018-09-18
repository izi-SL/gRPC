package com.isuru.wallet.serviceImpl;

import com.isuru.wallet.business.service.AccountService;
import com.isuru.wallet.business.service.TransactionService;
import com.isuru.wallet.business.service.impl.AccountServiceImpl;
import com.isuru.wallet.business.service.impl.TransactionServiceImpl;
import com.isuru.wallet.service.*;
import com.isuru.wallet.status.message.ResponseMessage;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Isuru Gajanayake
 * @see com.isuru.wallet.service.WalletServiceGrpc.WalletServiceImplBase
 */
public class WalletServiceImpl extends WalletServiceGrpc.WalletServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);


    private final TransactionService transactionService = new TransactionServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();

    /**
     * <pre>
     * Make a deposit to given user with EUR, USD, GBP currencies.
     * Invalid currencies throw an @{@link com.isuru.wallet.exception.UnknownCurrencyException} exception.
     * </pre>
     *
     * @param request          @{@link DepositRequest}
     * @param responseObserver @{@link StreamObserver<TransactionResponse>}
     */
    @Override
    public void deposit(DepositRequest request, StreamObserver<TransactionResponse> responseObserver) {
        LOGGER.info("User :"+ request.getDeposit().getUserId() + " invoked deposit");
        final String status = this.transactionService.createCashDeposit(request);
        if (status == ResponseMessage.TRANSACTION_SUCCESS) {
            responseObserver.onNext(
                    TransactionResponse.newBuilder()
                            .setMessage(ResponseMessage.TRANSACTION_SUCCESS)
                            .build()
            );
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.INTERNAL
                    .withDescription(WalletServiceExceptionHandler.getExceptionMessage(status))
                    .asRuntimeException());
        }

    }

    /**
     * <pre>
     * Make a withdrawal from given user, if and only if the
     * user have the sufficient balance in wallet.Otherwise,
     * throw an @{@link com.isuru.wallet.exception.InsufficientFundException} exception.
     * </pre>
     *
     * @param request          @{@link WithdrawalRequest}
     * @param responseObserver @{@link StreamObserver<TransactionResponse>}
     */
    @Override
    public void withdraw(WithdrawalRequest request, StreamObserver<TransactionResponse> responseObserver) {
        LOGGER.info("User : "+ request.getWithdrawal().getUserId() + " invoked withdraw");
        final String status = this.transactionService.createCashWithdrawal(request);
        if (status == ResponseMessage.TRANSACTION_SUCCESS) {
            responseObserver.onNext(
                    TransactionResponse.newBuilder()
                            .setMessage(ResponseMessage.TRANSACTION_SUCCESS)
                            .build()
            );
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.INTERNAL
                    .withDescription(WalletServiceExceptionHandler.getExceptionMessage(status))
                    .asRuntimeException());
        }
    }

    /**
     * <pre>
     * Return balances of given user's accounts
     * </pre>
     *
     * @param request          @{@link BalanceRequest}
     * @param responseObserver @{@link StreamObserver<BalanceResponse>}
     */
    @Override
    public void getBalance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        LOGGER.info("User : "+ request.getUserId() + " invoked getBalance");
        final BalanceResponse response = this.accountService.checkAccountsBalance(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
