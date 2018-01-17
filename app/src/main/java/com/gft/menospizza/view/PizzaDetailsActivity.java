package com.gft.menospizza.view;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.gft.menospizza.R;
import com.gft.menospizza.model.Pizza;
import com.gft.menospizza.util.Constants;
import com.squareup.picasso.Picasso;

public class PizzaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        Pizza pizza = getIntent().getParcelableExtra(Constants.PIZZA);

        ImageView imgPizza = findViewById(R.id.imgPizza);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(pizza.getName().toUpperCase());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);

        Picasso.with(this).load(pizza.getImageUrl()).into(imgPizza);

        setToolbar(toolbar);
    }

    private void setToolbar(final Toolbar toolbar) {
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
}
