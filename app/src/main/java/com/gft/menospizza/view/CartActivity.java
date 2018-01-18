package com.gft.menospizza.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gft.menospizza.R;
import com.gft.menospizza.adapter.CartAdapter;
import com.gft.menospizza.interfaces.CustomRequestCallback;
import com.gft.menospizza.manager.CartManager;
import com.gft.menospizza.manager.OrderManager;
import com.gft.menospizza.model.Drink;
import com.gft.menospizza.model.Order;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CartActivity extends BaseActivity implements CartAdapter.OnRemoveItemListener {

    private static String TAG = CartActivity.class.getSimpleName();

    private TextView tvTotalPrice;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActivity = CartActivity.this;

        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        setToolbar();

        Button btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(mActivity);
                progressDialog.setTitle("Sending order");
                progressDialog.setMessage("Wait...");

                Order order = new Order();
                order.setPizzas(CartManager.getPizzas());

                List<Integer> ids = new ArrayList<>();

                for (Drink drink : CartManager.getDrinks())
                    ids.add(drink.getId());

                order.setDrinks(ids);

                new OrderManager().sendOrder(new CustomRequestCallback<Response<ResponseBody>>() {
                    @Override
                    public void onSuccess(Response<ResponseBody> response) {
                        progressDialog.dismiss();

                        CartManager.removeAll();

                        Intent intent = new Intent(mActivity, ConfirmationActivity.class);
                        startActivity(intent);
                        finish();

                        Log.d(TAG, response.message());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        progressDialog.dismiss();
                        Log.d(TAG, throwable.getMessage());
                    }

                    @Override
                    public void onFailure(String message) {
                        progressDialog.dismiss();
                        Log.d(TAG, message);
                    }
                }, order);

            }
        });

    }

    private void updatePrice() {
        CartManager manager = new CartManager();
        String price = manager.formatPrice(manager.calculatePrice());
        tvTotalPrice.setText(price);
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.txt_cart));
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_red));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_24dp));
    }

    @Override
    protected void onResume() {
        super.onResume();

        updatePrice();

        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView rvCart = findViewById(R.id.rvCart);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        rvCart.setLayoutManager(mLayoutManager);
        rvCart.setItemAnimator(new DefaultItemAnimator());
        CartAdapter adapter = new CartAdapter(CartManager.getPizzas(), CartManager.getDrinks(), mActivity);
        rvCart.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.itAddDrink:
                Intent intent = new Intent(mActivity, DrinkActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRemovePizza() {
        updatePrice();
    }

    @Override
    public void onRemoveDrink() {
        updatePrice();
    }
}
