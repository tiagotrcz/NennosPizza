package com.gft.menospizza.view;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gft.menospizza.manager.CartManager;
import com.gft.menospizza.model.Cart;
import com.gft.menospizza.util.Constants;
import com.google.gson.Gson;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();

        Cart cart = new Cart();
        cart.setPizzas(CartManager.getPizzas());
        cart.setDrinks(CartManager.getDrinks());

        String json = gson.toJson(cart);
        prefsEditor.putString(Constants.CART, json);
        prefsEditor.apply();
    }
}
