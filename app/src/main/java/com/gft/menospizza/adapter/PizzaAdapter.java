package com.gft.menospizza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gft.menospizza.R;
import com.gft.menospizza.manager.PizzaManager;
import com.gft.menospizza.model.BasePizzaPrice;
import com.gft.menospizza.model.Ingredient;
import com.gft.menospizza.model.Pizza;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tiago on 16/01/18.
 */

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private BasePizzaPrice mPizzas;
    private List<Ingredient> mIngredients;
    private Context mContext;
    private OnClick mListener;

    private PizzaManager mManager;

    public PizzaAdapter(BasePizzaPrice pizzas, List<Ingredient> ingredients, Context context) {
        this.mPizzas = pizzas;
        this.mIngredients = ingredients;
        this.mContext = context;
        this.mListener = (OnClick) context;

        this.mManager = new PizzaManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = null;

        View view = inflater.inflate(R.layout.item_pizza_list, parent, false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pizza pizza = mPizzas.getPizzas().get(position);

        final double pizzaPrice = mManager.calculatePrice(mPizzas.getBasePrice(), pizza.getIngredients(), mIngredients);

        Picasso.with(mContext).load(pizza.getImageUrl()).into(holder.imgPizza);

        holder.tvTitle.setText(pizza.getName());

        holder.tvDescription.setText(new PizzaManager().getDescription(pizza.getIngredients(), mIngredients));

        holder.btnAddToCard.setText(mManager.formatPrice(pizzaPrice));

        holder.btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setTotalPrice(pizzaPrice);
                mListener.onAddToCart(pizza);
            }
        });

        holder.imgPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showPizzaDetails(pizzaPrice, pizza);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPizzas.getPizzas().size();
    }

    public interface OnClick {
        void onAddToCart(Pizza pizza);

        void showPizzaDetails(double basePrice, Pizza pizza);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPizza;
        TextView tvTitle;
        TextView tvDescription;
        Button btnAddToCard;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            imgPizza = itemView.findViewById(R.id.imgPizza);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnAddToCard = itemView.findViewById(R.id.btnAddToCart);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
