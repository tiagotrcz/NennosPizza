package com.gft.menospizza.business;

import com.gft.menospizza.model.Drink;
import com.gft.menospizza.model.Pizza;
import com.gft.menospizza.util.PriceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class CartBusiness {

    private static List<Pizza> mPizzas;
    private static List<Drink> mDrinks;
    private static List<Integer> mDrinksIds;

    public CartBusiness() {
    }

    public static List<Pizza> getPizzas() {
        if (mPizzas == null)
            return mPizzas = new ArrayList<>();
        else
            return mPizzas;
    }

    public static List<Drink> getDrinks() {
        if (mDrinks == null)
            return mDrinks = new ArrayList<>();
        else
            return mDrinks;
    }

    public static void removeAll() {
        mPizzas = null;
        mDrinks = null;
        mDrinksIds = null;
    }

    public static List<Integer> getDrinksIds() {
        if (mDrinksIds == null)
            return mDrinksIds = new ArrayList<>();
        else
            return mDrinksIds;
    }

    public double calculatePrice() {
        double price = 0;

        if (mPizzas != null)
            for (Pizza pizza : mPizzas)
                price += pizza.getTotalPrice();

        if (mDrinks != null)
            for (Drink drink : mDrinks)
                price += drink.getPrice();

        return price;
    }

    public String formatPrice(double price) {
        return PriceUtil.formatPrice(price);
    }

}
