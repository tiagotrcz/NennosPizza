package com.gft.menospizza.interfaces;

import com.gft.menospizza.model.Ingredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tiago on 16/01/18.
 */

public interface IngredientService {

    @GET("ozt3z/")
    Call<List<Ingredient>> getIngredients();

}
