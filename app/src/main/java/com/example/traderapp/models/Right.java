package com.example.traderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Right {
    @SerializedName("Side")
    @Expose
    private Integer side;
    @SerializedName("L")
    @Expose
    private List<Integer> l = null;

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public List<Integer> getL() {
        return l;
    }

    public void setL(List<Integer> l) {
        this.l = l;
    }
}
