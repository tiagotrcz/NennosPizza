package com.gft.menospizza.manager;

import com.gft.menospizza.business.CartBusiness;
import com.gft.menospizza.model.Drink;
import com.gft.menospizza.model.Pizza;

import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class CartManager {

    private CartBusiness mBusiness;

    public CartManager() {
        mBusiness = new CartBusiness();
    }

    public static List<Pizza> getPizzas() {
        return CartBusiness.getPizzas();
    }

    public static List<Drink> getDrinks() {
        return CartBusiness.getDrinks();
    }

    public static List<Integer> getDrinksIds() {
        return CartBusiness.getDrinksIds();
    }

    public static void removeAll() {
        CartBusiness.removeAll();
    }

    public double calculatePrice() {
        return mBusiness.calculatePrice();
    }

    public String formatPrice(double price) {
        return mBusiness.formatPrice(price);
    }

}
