package com.gft.menospizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tiago on 16/01/18.
 */

public class BasePizzaPrice implements Parcelable{

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.basePrice);
        dest.writeTypedList(this.pizzas);
    }

    public BasePizzaPrice() {
    }

    protected BasePizzaPrice(Parcel in) {
        this.basePrice = in.readDouble();
        this.pizzas = in.createTypedArrayList(Pizza.CREATOR);
    }

    public static final Creator<BasePizzaPrice> CREATOR = new Creator<BasePizzaPrice>() {
        @Override
        public BasePizzaPrice createFromParcel(Parcel source) {
            return new BasePizzaPrice(source);
        }

        @Override
        public BasePizzaPrice[] newArray(int size) {
            return new BasePizzaPrice[size];
        }
    };
}
