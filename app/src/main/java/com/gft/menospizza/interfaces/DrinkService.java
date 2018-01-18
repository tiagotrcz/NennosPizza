package com.gft.menospizza.interfaces;

import com.gft.menospizza.model.Drink;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tiago on 17/01/18.
 */

public interface DrinkService {

    @GET("150da7/")
    Call<List<Drink>> getDrinks();

}
