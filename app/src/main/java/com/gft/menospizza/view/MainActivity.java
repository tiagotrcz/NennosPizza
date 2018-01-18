package com.gft.menospizza.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gft.menospizza.R;
import com.gft.menospizza.adapter.PizzaAdapter;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.manager.CartManager;
import com.gft.menospizza.manager.IngredientsManager;
import com.gft.menospizza.manager.PizzaManager;
import com.gft.menospizza.model.BasePizzaPrice;
import com.gft.menospizza.model.Cart;
import com.gft.menospizza.model.Ingredient;
import com.gft.menospizza.model.Pizza;
import com.gft.menospizza.util.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends BaseActivity implements PizzaAdapter.OnClick {

    private static String TAG = MainActivity.class.getSimpleName();

    private Activity mActivity;

    private RecyclerView mRecycler;
    private ProgressBar mProgressBar;

    private PizzaAdapter mAdapter;

    private List<Ingredient> mIngredients;

    private double mBasePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = MainActivity.this;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);

        getSavedCart(prefs);

        setProgressBar();

        setToolbar();

        setRecyclerView();

        getIngredients();
    }

    private void getSavedCart(SharedPreferences prefs) {
        Gson gson = new Gson();
        String json = prefs.getString(Constants.CART, "");
        Cart cart = gson.fromJson(json, Cart.class);

        if (cart != null) {
            CartManager.getPizzas().addAll(cart.getPizzas());
            CartManager.getDrinks().addAll(cart.getDrinks());
        }
    }

    private void setProgressBar() {
        mProgressBar = findViewById(R.id.progressBar);

        mProgressBar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.app_red));
    }

    private void setRecyclerView() {
        mRecycler = findViewById(R.id.rvDrinks);

        mRecycler.addItemDecoration(new DividerItemDecoration(mActivity,
                DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false);

        mRecycler.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(mActivity, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.itCreate:
                Pizza pizza = new Pizza();
                pizza.setName("Custom Pizza");
                showPizzaDetails(mBasePrice, pizza);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.txt_app_title));
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_red));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_cart));
    }

    private void getIngredients() {
        new IngredientsManager().getIngredients(new CustomRequestCallback<List<Ingredient>>() {
            @Override
            public void onSuccess(List<Ingredient> response) {
                mIngredients = response;

                getPizzas(mIngredients);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPizzas(final List<Ingredient> ingredients) {
        new PizzaManager().getPizzas(new CustomRequestCallback<Response<BasePizzaPrice>>() {
            @Override
            public void onSuccess(Response<BasePizzaPrice> response) {
                BasePizzaPrice basePizzaPrice = new BasePizzaPrice();
                basePizzaPrice.setBasePrice(response.body().getBasePrice());
                basePizzaPrice.setPizzas(response.body().getPizzas());

                setAdapter(basePizzaPrice, ingredients);

                mBasePrice = basePizzaPrice.getBasePrice();

                mProgressBar.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(BasePizzaPrice basePizzaPrice, List<Ingredient> ingredients) {
        mAdapter = new PizzaAdapter(basePizzaPrice, ingredients, mActivity);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onAddToCart(Pizza pizza) {
        CartManager.getPizzas().add(pizza);

        Toast.makeText(mActivity, "ADDED TO CART", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPizzaDetails(double basePrice, Pizza pizza) {
        Intent intent = new Intent(mActivity, PizzaDetailsActivity.class);
        intent.putExtra(Constants.BASE_PRICE, basePrice);
        intent.putExtra(Constants.PIZZA, pizza);
        intent.putParcelableArrayListExtra(Constants.INGREDIENTS, (ArrayList<? extends Parcelable>) mIngredients);
        startActivity(intent);
    }

}
