package com.gft.menospizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 16/01/18.
 */

public class Pizza implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("ingredients")
    private List<Integer> ingredients;
    private double totalPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeList(this.ingredients);
        dest.writeDouble(this.totalPrice);
    }

    public Pizza() {
    }

    protected Pizza(Parcel in) {
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.ingredients = new ArrayList<Integer>();
        in.readList(this.ingredients, Integer.class.getClassLoader());
        this.totalPrice = in.readDouble();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel source) {
            return new Pizza(source);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };
}
