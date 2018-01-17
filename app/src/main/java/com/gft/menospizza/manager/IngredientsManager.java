package com.gft.menospizza.manager;

import com.gft.menospizza.business.IngredientsBusiness;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.Ingredient;

import java.util.List;

/**
 * Created by tiago on 16/01/18.
 */

public class IngredientsManager {

    private IngredientsBusiness business;

    public IngredientsManager() {
        this.business = new IngredientsBusiness();
    }

    public void getIngredients(CustomRequestCallback<List<Ingredient>> callback) {
        business.getIngredients(callback);
    }
}
