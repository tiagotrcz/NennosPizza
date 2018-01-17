package com.gft.menospizza.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tiago on 16/01/18.
 */

public class Drink {

    @SerializedName("id")
    private int id;
    @SerializedName("price")
    private double price;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
