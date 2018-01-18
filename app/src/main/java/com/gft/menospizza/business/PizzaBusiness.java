package com.gft.menospizza.business;

import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.BasePizzaPrice;
import com.gft.menospizza.model.Ingredient;
import com.gft.menospizza.provider.PizzaProvider;
import com.gft.menospizza.util.Constants;
import com.gft.menospizza.util.PriceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 16/01/18.
 */

public class PizzaBusiness {

    private PizzaProvider provider;

    public PizzaBusiness() {
        provider = new PizzaProvider(Constants.BASE_URL);
    }

    public void getPizzas(final CustomRequestCallback<Response<BasePizzaPrice>> callback) {
        provider.getPizzas().enqueue(new Callback<BasePizzaPrice>() {
            @Override
            public void onResponse(Call<BasePizzaPrice> call, Response<BasePizzaPrice> response) {
                if (response.isSuccessful())
                    callback.onSuccess(response);
                else
                    callback.onFailure(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<BasePizzaPrice> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public String formatPrice(double price) {
        return PriceUtil.formatPrice(price);
    }

    public double calculatePrice(double basePrice, List<Integer> ingredientsIds, List<Ingredient> ingredients) {
        return PriceUtil.calculatePrice(basePrice, ingredientsIds, ingredients);
    }

    public String getDescription(List<Integer> ingredientsIds, List<Ingredient> ingredients) {
        StringBuilder ingredientsConcat = new StringBuilder();

        int size = 0;

        for (Ingredient ingredient : ingredients) {
            for (Integer id : ingredientsIds) {
                if (id == ingredient.getId()) {
                    if (size == ingredientsIds.size() - 1)
                        ingredientsConcat.append(ingredient.getName()).append(".");
                    else
                        ingredientsConcat.append(ingredient.getName()).append(", ");

                    size++;
                }
            }
        }

        return ingredientsConcat.toString();
    }

}
