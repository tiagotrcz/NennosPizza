package com.gft.menospizza.provider;

import com.gft.menospizza.interfaces.PizzaService;
import com.gft.menospizza.model.BasePizzaPrice;

import retrofit2.Call;

/**
 * Created by tiago on 16/01/18.
 */

public class PizzaProvider extends BaseRestProvider {

    private PizzaService service;

    public PizzaProvider(String url) {
        super(url);
        service = retrofit.create(PizzaService.class);
    }

    public Call<BasePizzaPrice> getPizzas() {
        return service.getPizzas();
    }

}
