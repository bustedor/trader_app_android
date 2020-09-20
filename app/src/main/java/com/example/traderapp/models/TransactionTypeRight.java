package com.example.traderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionTypeRight {
    @SerializedName("ExchangeID")
    @Expose
    private Integer exchangeID;
    @SerializedName("Rights")
    @Expose
    private List<Right> rights = null;

    public Integer getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(Integer exchangeID) {
        this.exchangeID = exchangeID;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }
}
