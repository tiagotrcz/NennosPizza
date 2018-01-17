package com.gft.menospizza.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tiago on 16/01/18.
 */

public class BasePizzaPrice {

    @SerializedName("basePrice")
    private double basePrice;
    @SerializedName("pizzas")
    private List<Pizza> pizzas;

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
