package com.gft.menospizza.manager;

import com.gft.menospizza.business.DrinkBusiness;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.model.Drink;

import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class DrinkManager {

    private DrinkBusiness business;

    public DrinkManager() {
        this.business = new DrinkBusiness();
    }

    public void getDrinks(CustomRequestCallback<List<Drink>> callback) {
        business.getDrinks(callback);
    }

    public String formatPrice(double price) {
        return business.formatPrice(price);
    }

}
