package com.gft.menospizza.business;

import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.Ingredient;
import com.gft.menospizza.provider.IngredientsProvider;
import com.gft.menospizza.util.Constants;
import com.gft.menospizza.util.PriceUtil;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 16/01/18.
 */

public class IngredientsBusiness {

    private IngredientsProvider provider;

    public IngredientsBusiness() {
        provider = new IngredientsProvider(Constants.BASE_URL);
    }

    public void getIngredients(final CustomRequestCallback<List<Ingredient>> callback) {
        provider.getIngredients().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onFailure(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public String formatPrice(double price) {
        return PriceUtil.formatPrice(price);
    }

    public boolean isDefaultIngredient(int ingredientId, List<Integer> ids) {
        for (Integer ingredient : ids)
            if (ingredient == ingredientId)
                return true;

        return false;
    }

}
