package com.example.traderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountItem {
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("ExchangeAccountID")
    @Expose
    private ExchangeAccountID exchangeAccountID;
    @SerializedName("AccountRights")
    @Expose
    private List<AccountRight> accountRights = null;
    @SerializedName("TransactionTypeRights")
    @Expose
    private List<TransactionTypeRight> transactionTypeRights = null;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public ExchangeAccountID getExchangeAccountID() {
        return exchangeAccountID;
    }

    public void setExchangeAccountID(ExchangeAccountID exchangeAccountID) {
        this.exchangeAccountID = exchangeAccountID;
    }

    public List<AccountRight> getAccountRights() {
        return accountRights;
    }

    public void setAccountRights(List<AccountRight> accountRights) {
        this.accountRights = accountRights;
    }

    public List<TransactionTypeRight> getTransactionTypeRights() {
        return transactionTypeRights;
    }

    public void setTransactionTypeRights(List<TransactionTypeRight> transactionTypeRights) {
        this.transactionTypeRights = transactionTypeRights;
    }
}
