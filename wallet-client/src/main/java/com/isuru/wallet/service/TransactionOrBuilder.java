// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: wallet/wallet.proto

package com.isuru.wallet.service;

public interface TransactionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:wallet.Transaction)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 user_id = 1;</code>
   */
  int getUserId();

  /**
   * <code>double amount = 2;</code>
   */
  double getAmount();

  /**
   * <code>.wallet.CurrencyType currency = 3;</code>
   */
  int getCurrencyValue();
  /**
   * <code>.wallet.CurrencyType currency = 3;</code>
   */
  com.isuru.wallet.service.CurrencyType getCurrency();
}
