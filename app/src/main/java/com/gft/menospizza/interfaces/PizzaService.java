package com.gft.menospizza.interfaces;

import com.gft.menospizza.model.BasePizzaPrice;
import com.gft.menospizza.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tiago on 16/01/18.
 */

public interface PizzaService {

    @GET("dokm7/")
    Call<BasePizzaPrice> getPizzas();
}
