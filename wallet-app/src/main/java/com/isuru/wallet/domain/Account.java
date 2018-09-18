package com.isuru.wallet.domain;

import com.isuru.wallet.service.CurrencyType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Account data holder
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "default_currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType defaultCurrencyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Version
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CurrencyType getDefaultCurrencyType() {
        return defaultCurrencyType;
    }

    public void setDefaultCurrencyType(CurrencyType defaultCurrencyType) {
        this.defaultCurrencyType = defaultCurrencyType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
