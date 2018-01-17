package com.gft.menospizza.manager;

import com.gft.menospizza.business.PizzaBusiness;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.BasePizzaPrice;
import com.gft.menospizza.model.Ingredient;

import java.util.List;

import retrofit2.Response;

/**
 * Created by tiago on 16/01/18.
 */

public class PizzaManager {

    private PizzaBusiness mBusiness;

    public PizzaManager() {
        this.mBusiness = new PizzaBusiness();
    }

    public void getPizzas(CustomRequestCallback<Response<BasePizzaPrice>> callback) {
        this.mBusiness.getPizzas(callback);
    }

    public String calculatePrice(double basePrice, List<Integer> ingredientsIds, List<Ingredient> ingredients) {
        return this.mBusiness.calculatePrice(basePrice, ingredientsIds, ingredients);
    }

    public String getDescription(List<Integer> ingredientsIds, List<Ingredient> ingredients) {
        return this.mBusiness.getDescription(ingredientsIds, ingredients);
    }

}
