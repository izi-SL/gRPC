// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: wallet/wallet.proto

package com.isuru.wallet.service;

public interface AccountBalanceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:wallet.AccountBalance)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 user_id = 1;</code>
   */
  int getUserId();

  /**
   * <code>int32 account_id = 2;</code>
   */
  int getAccountId();

  /**
   * <code>string user_email = 3;</code>
   */
  java.lang.String getUserEmail();
  /**
   * <code>string user_email = 3;</code>
   */
  com.google.protobuf.ByteString
      getUserEmailBytes();

  /**
   * <code>double balance = 4;</code>
   */
  double getBalance();

  /**
   * <code>.wallet.CurrencyType currency = 5;</code>
   */
  int getCurrencyValue();
  /**
   * <code>.wallet.CurrencyType currency = 5;</code>
   */
  com.isuru.wallet.service.CurrencyType getCurrency();
}
