package com.gft.menospizza.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tiago on 18/01/18.
 */

public class Order {

    @SerializedName("pizzas")
    @Expose
    private List<Pizza> pizzas;
    @SerializedName("drinks")
    @Expose
    private List<Integer> drinks;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Integer> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Integer> drinks) {
        this.drinks = drinks;
    }

}
