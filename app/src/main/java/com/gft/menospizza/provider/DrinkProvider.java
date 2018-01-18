package com.gft.menospizza.provider;

import com.gft.menospizza.interfaces.DrinkService;
import com.gft.menospizza.model.Drink;

import java.util.List;

import retrofit2.Call;

/**
 * Created by tiago on 17/01/18.
 */

public class DrinkProvider extends BaseRestProvider {

    private DrinkService mService;

    public DrinkProvider(String url) {
        super(url);
        mService = retrofit.create(DrinkService.class);
    }

    public Call<List<Drink>> getDrinks() {
        return mService.getDrinks();
    }
}
