package com.gft.menospizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class Cart implements Parcelable {

    @SerializedName("pizzas")
    @Expose
    private List<Pizza> pizzas;
    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.pizzas);
        dest.writeList(this.drinks);
    }

    public Cart() {
    }

    protected Cart(Parcel in) {
        this.pizzas = in.createTypedArrayList(Pizza.CREATOR);
        this.drinks = new ArrayList<Drink>();
        in.readList(this.drinks, Drink.class.getClassLoader());
    }

    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
}
