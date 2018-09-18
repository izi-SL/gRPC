package com.isuru.wallet.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *  Service that deposit, withdraw user money and keep tack of the
 *  monetary balance in the system.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: wallet/wallet.proto")
public final class WalletServiceGrpc {

  private WalletServiceGrpc() {}

  public static final String SERVICE_NAME = "wallet.WalletService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.isuru.wallet.service.DepositRequest,
      com.isuru.wallet.service.TransactionResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = com.isuru.wallet.service.DepositRequest.class,
      responseType = com.isuru.wallet.service.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.isuru.wallet.service.DepositRequest,
      com.isuru.wallet.service.TransactionResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<com.isuru.wallet.service.DepositRequest, com.isuru.wallet.service.TransactionResponse> getDepositMethod;
    if ((getDepositMethod = WalletServiceGrpc.getDepositMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getDepositMethod = WalletServiceGrpc.getDepositMethod) == null) {
          WalletServiceGrpc.getDepositMethod = getDepositMethod = 
              io.grpc.MethodDescriptor.<com.isuru.wallet.service.DepositRequest, com.isuru.wallet.service.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wallet.WalletService", "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.isuru.wallet.service.DepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.isuru.wallet.service.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("Deposit"))
                  .build();
          }
        }
     }
     return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.isuru.wallet.service.WithdrawalRequest,
      com.isuru.wallet.service.TransactionResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = com.isuru.wallet.service.WithdrawalRequest.class,
      responseType = com.isuru.wallet.service.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.isuru.wallet.service.WithdrawalRequest,
      com.isuru.wallet.service.TransactionResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<com.isuru.wallet.service.WithdrawalRequest, com.isuru.wallet.service.TransactionResponse> getWithdrawMethod;
    if ((getWithdrawMethod = WalletServiceGrpc.getWithdrawMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getWithdrawMethod = WalletServiceGrpc.getWithdrawMethod) == null) {
          WalletServiceGrpc.getWithdrawMethod = getWithdrawMethod = 
              io.grpc.MethodDescriptor.<com.isuru.wallet.service.WithdrawalRequest, com.isuru.wallet.service.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wallet.WalletService", "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.isuru.wallet.service.WithdrawalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.isuru.wallet.service.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("Withdraw"))
                  .build();
          }
        }
     }
     return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.isuru.wallet.service.BalanceRequest,
      com.isuru.wallet.service.BalanceResponse> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = com.isuru.wallet.service.BalanceRequest.class,
      responseType = com.isuru.wallet.service.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.isuru.wallet.service.BalanceRequest,
      com.isuru.wallet.service.BalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<com.isuru.wallet.service.BalanceRequest, com.isuru.wallet.service.BalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = WalletServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getGetBalanceMethod = WalletServiceGrpc.getGetBalanceMethod) == null) {
          WalletServiceGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<com.isuru.wallet.service.BalanceRequest, com.isuru.wallet.service.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wallet.WalletService", "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.isuru.wallet.service.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.isuru.wallet.service.BalanceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("GetBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WalletServiceStub newStub(io.grpc.Channel channel) {
    return new WalletServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WalletServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WalletServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WalletServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WalletServiceFutureStub(channel);
  }

  /**
   * <pre>
   *  Service that deposit, withdraw user money and keep tack of the
   *  monetary balance in the system.
   * </pre>
   */
  public static abstract class WalletServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Make a deposit to given user with EUR, USD, GBP currencies.
     * Invalid currencies throw an &lt;b&gt;Unknown Currency&lt;/b&gt; exception.
     * </pre>
     */
    public void deposit(com.isuru.wallet.service.DepositRequest request,
        io.grpc.stub.StreamObserver<com.isuru.wallet.service.TransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Make a withdrawal from given user, if and only if the
     * user have the sufficient balance in wallet.Otherwise,
     * throw an &lt;b&gt;Insufficient Funds&lt;/b&gt; exception.
     * </pre>
     */
    public void withdraw(com.isuru.wallet.service.WithdrawalRequest request,
        io.grpc.stub.StreamObserver<com.isuru.wallet.service.TransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return balance of given user
     * </pre>
     */
    public void getBalance(com.isuru.wallet.service.BalanceRequest request,
        io.grpc.stub.StreamObserver<com.isuru.wallet.service.BalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.isuru.wallet.service.DepositRequest,
                com.isuru.wallet.service.TransactionResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.isuru.wallet.service.WithdrawalRequest,
                com.isuru.wallet.service.TransactionResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.isuru.wallet.service.BalanceRequest,
                com.isuru.wallet.service.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .build();
    }
  }

  /**
   * <pre>
   *  Service that deposit, withdraw user money and keep tack of the
   *  monetary balance in the system.
   * </pre>
   */
  public static final class WalletServiceStub extends io.grpc.stub.AbstractStub<WalletServiceStub> {
    private WalletServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Make a deposit to given user with EUR, USD, GBP currencies.
     * Invalid currencies throw an &lt;b&gt;Unknown Currency&lt;/b&gt; exception.
     * </pre>
     */
    public void deposit(com.isuru.wallet.service.DepositRequest request,
        io.grpc.stub.StreamObserver<com.isuru.wallet.service.TransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Make a withdrawal from given user, if and only if the
     * user have the sufficient balance in wallet.Otherwise,
     * throw an &lt;b&gt;Insufficient Funds&lt;/b&gt; exception.
     * </pre>
     */
    public void withdraw(com.isuru.wallet.service.WithdrawalRequest request,
        io.grpc.stub.StreamObserver<com.isuru.wallet.service.TransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return balance of given user
     * </pre>
     */
    public void getBalance(com.isuru.wallet.service.BalanceRequest request,
        io.grpc.stub.StreamObserver<com.isuru.wallet.service.BalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *  Service that deposit, withdraw user money and keep tack of the
   *  monetary balance in the system.
   * </pre>
   */
  public static final class WalletServiceBlockingStub extends io.grpc.stub.AbstractStub<WalletServiceBlockingStub> {
    private WalletServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Make a deposit to given user with EUR, USD, GBP currencies.
     * Invalid currencies throw an &lt;b&gt;Unknown Currency&lt;/b&gt; exception.
     * </pre>
     */
    public com.isuru.wallet.service.TransactionResponse deposit(com.isuru.wallet.service.DepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Make a withdrawal from given user, if and only if the
     * user have the sufficient balance in wallet.Otherwise,
     * throw an &lt;b&gt;Insufficient Funds&lt;/b&gt; exception.
     * </pre>
     */
    public com.isuru.wallet.service.TransactionResponse withdraw(com.isuru.wallet.service.WithdrawalRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return balance of given user
     * </pre>
     */
    public com.isuru.wallet.service.BalanceResponse getBalance(com.isuru.wallet.service.BalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *  Service that deposit, withdraw user money and keep tack of the
   *  monetary balance in the system.
   * </pre>
   */
  public static final class WalletServiceFutureStub extends io.grpc.stub.AbstractStub<WalletServiceFutureStub> {
    private WalletServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Make a deposit to given user with EUR, USD, GBP currencies.
     * Invalid currencies throw an &lt;b&gt;Unknown Currency&lt;/b&gt; exception.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.isuru.wallet.service.TransactionResponse> deposit(
        com.isuru.wallet.service.DepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Make a withdrawal from given user, if and only if the
     * user have the sufficient balance in wallet.Otherwise,
     * throw an &lt;b&gt;Insufficient Funds&lt;/b&gt; exception.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.isuru.wallet.service.TransactionResponse> withdraw(
        com.isuru.wallet.service.WithdrawalRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return balance of given user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.isuru.wallet.service.BalanceResponse> getBalance(
        com.isuru.wallet.service.BalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_GET_BALANCE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WalletServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WalletServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSIT:
          serviceImpl.deposit((com.isuru.wallet.service.DepositRequest) request,
              (io.grpc.stub.StreamObserver<com.isuru.wallet.service.TransactionResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((com.isuru.wallet.service.WithdrawalRequest) request,
              (io.grpc.stub.StreamObserver<com.isuru.wallet.service.TransactionResponse>) responseObserver);
          break;
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((com.isuru.wallet.service.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.isuru.wallet.service.BalanceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class WalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WalletServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.isuru.wallet.service.Wallet.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WalletService");
    }
  }

  private static final class WalletServiceFileDescriptorSupplier
      extends WalletServiceBaseDescriptorSupplier {
    WalletServiceFileDescriptorSupplier() {}
  }

  private static final class WalletServiceMethodDescriptorSupplier
      extends WalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WalletServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WalletServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WalletServiceFileDescriptorSupplier())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getGetBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
