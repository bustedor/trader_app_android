package com.example.traderapp.models;

import com.example.traderapp.models.Item;
import com.example.traderapp.models.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PortfolioResponse {
    @SerializedName("Result")
    @Expose
    private Result result;
    @SerializedName("Header")
    @Expose
    private List<String> header = null;
    @SerializedName("Item")
    @Expose
    private List<Item> itemList = null;
    @SerializedName("Other")
    @Expose
    private List<Object> other = null;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItem(List<Item> item) {
        this.itemList = item;
    }

    public List<Object> getOther() {
        return other;
    }

    public void setOther(List<Object> other) {
        this.other = other;
    }

}
