package com.gft.menospizza.util;

import com.gft.menospizza.model.Ingredient;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class PriceUtil {

    public static double calculatePrice(double basePrice, List<Integer> ingredientsIds, List<Ingredient> ingredients) {
        double price = 0;

        for (Ingredient ingredient : ingredients)
            for (Integer id : ingredientsIds)
                if (id == ingredient.getId())
                    price += ingredient.getPrice();

        price += basePrice;

        return price;
    }

    public static String formatPrice(double price) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setCurrency(Currency.getInstance("BRL"));

        String priceFormatted = format.format(price);

        return priceFormatted.replace('.', ',');
    }

}
