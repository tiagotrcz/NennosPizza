package com.gft.menospizza.provider;

import com.gft.menospizza.interfaces.IngredientService;
import com.gft.menospizza.model.Ingredient;

import java.util.List;

import retrofit2.Call;

/**
 * Created by tiago on 16/01/18.
 */

public class IngredientsProvider extends BaseRestProvider {

    private IngredientService service;

    public IngredientsProvider(String url) {
        super(url);
        service = retrofit.create(IngredientService.class);
    }

    public Call<List<Ingredient>> getIngredients() {
        return service.getIngredients();
    }

}
