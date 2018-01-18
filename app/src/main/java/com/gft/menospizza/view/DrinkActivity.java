package com.gft.menospizza.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gft.menospizza.R;
import com.gft.menospizza.adapter.DrinksAdapter;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.manager.CartManager;
import com.gft.menospizza.manager.DrinkManager;
import com.gft.menospizza.model.Drink;

import java.util.List;

public class DrinkActivity extends BaseActivity implements DrinksAdapter.OnAddDrinkListener {

    private static String TAG = DrinkActivity.class.getSimpleName();

    private Activity mActivity;

    private RecyclerView mRvDrinks;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        mActivity = DrinkActivity.this;

        mProgressBar = findViewById(R.id.progressBar);

        setToolbar();

        getDrinks();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.txt_app_title));
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_red));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_24dp));
    }

    private void getDrinks() {
        new DrinkManager().getDrinks(new CustomRequestCallback<List<Drink>>() {
            @Override
            public void onSuccess(List<Drink> response) {
                setRecyclerView(response);

                mProgressBar.setVisibility(View.GONE);
                mRvDrinks.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onFailure(String message) {
                Log.e(TAG, message);
            }
        });
    }

    private void setRecyclerView(List<Drink> drinks) {
        mRvDrinks = findViewById(R.id.rvDrinks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRvDrinks.setLayoutManager(mLayoutManager);
        mRvDrinks.setItemAnimator(new DefaultItemAnimator());
        DrinksAdapter adapter = new DrinksAdapter(drinks, mActivity);
        mRvDrinks.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAdd(Drink drink) {
        CartManager.getDrinks().add(drink);
        CartManager.getDrinksIds().add(drink.getId());

        Toast.makeText(mActivity, "ADDED TO CART", Toast.LENGTH_SHORT).show();
    }
}
