package com.gft.menospizza.view;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gft.menospizza.R;
import com.gft.menospizza.adapter.IngredientsAdapter;
import com.gft.menospizza.manager.CartManager;
import com.gft.menospizza.manager.PizzaManager;
import com.gft.menospizza.model.Ingredient;
import com.gft.menospizza.model.Pizza;
import com.gft.menospizza.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PizzaDetailsActivity extends BaseActivity implements IngredientsAdapter.OnIngredientChangedListener, View.OnClickListener {

    private Activity mActivity;
    private Button btnAddToCart;
    private Pizza mPizza;

    private double mBasePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        mActivity = PizzaDetailsActivity.this;

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(this);

        mPizza = getIntent().getParcelableExtra(Constants.PIZZA);
        List<Ingredient> ingredients = getIntent().getParcelableArrayListExtra(Constants.INGREDIENTS);
        mBasePrice = getIntent().getDoubleExtra(Constants.BASE_PRICE, 0);

        setTextAddToCartButton();

        setRecyclerView(mPizza, ingredients);

        ImageView imgPizza = findViewById(R.id.imgPizza);

        Picasso.with(this).load(mPizza.getImageUrl()).into(imgPizza);

        setToolbar(mPizza);
    }

    private void setRecyclerView(Pizza pizza, List<Ingredient> ingredients) {
        RecyclerView rvIngredients = findViewById(R.id.rvCart);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        rvIngredients.setLayoutManager(mLayoutManager);
        rvIngredients.setItemAnimator(new DefaultItemAnimator());
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients, pizza.getIngredients(), mActivity);
        rvIngredients.setAdapter(adapter);
    }

    private void setToolbar(Pizza pizza) {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(pizza.getName().toUpperCase());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.app_red));

        AppBarLayout appBar = findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((toolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(toolbarLayout)))
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.app_red), PorterDuff.Mode.SRC_ATOP);
                else
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.app_white), PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAdd(Ingredient ingredient) {
        mBasePrice += ingredient.getPrice();
        mPizza.getIngredients().add(ingredient.getId());
        setTextAddToCartButton();
    }

    @Override
    public void onRemove(Ingredient ingredient) {
        mBasePrice -= ingredient.getPrice();
        mPizza.getIngredients().remove((Integer) ingredient.getId());
        setTextAddToCartButton();
    }

    private void setTextAddToCartButton() {
        btnAddToCart.setText(String.format(getResources().getString(R.string.txt_add_to_cart),
                new PizzaManager().formatPrice(mBasePrice)));
    }

    private void showToast() {
        Toast.makeText(mActivity, "ADDED TO CART", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        mPizza.setTotalPrice(mBasePrice);

        CartManager.getPizzas().add(mPizza);

        showToast();

        finish();
    }
}
