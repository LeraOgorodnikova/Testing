package com.testing.Models;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OurResponse<T> {

    @SerializedName("global_id")
    private long global_id;

    @SerializedName("Number")
    private int Number;

    @SerializedName("Cells")
    private List<T> Cells;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public List<T> getItems() {
        return Cells;
    }

    public void setItems(List<T> items) {
        Cells = items;
    }

    public long getGlobal_id() {
        return global_id;
    }

    public void setGlobal_id(long global_id) {
        this.global_id = global_id;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public List<T> getCells() {
        return Cells;
    }

    public void setCells(List<T> cells) {
        this.Cells = cells;
    }
}
