package com.gft.menospizza.business;

import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.Drink;
import com.gft.menospizza.provider.DrinkProvider;
import com.gft.menospizza.util.Constants;
import com.gft.menospizza.util.PriceUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 17/01/18.
 */

public class DrinkBusiness {

    private DrinkProvider provider;

    public DrinkBusiness() {
        provider = new DrinkProvider(Constants.BASE_URL);
    }

    public void getDrinks(final CustomRequestCallback<List<Drink>> callback) {
        provider.getDrinks().enqueue(new Callback<List<Drink>>() {
            @Override
            public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
                if (response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onFailure(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<List<Drink>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public String formatPrice(double price) {
        return PriceUtil.formatPrice(price);
    }

}
