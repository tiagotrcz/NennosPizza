package com.gft.menospizza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gft.menospizza.R;
import com.gft.menospizza.manager.DrinkManager;
import com.gft.menospizza.manager.PizzaManager;
import com.gft.menospizza.model.Drink;
import com.gft.menospizza.model.Pizza;

import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private static final int PIZZA = 0;
    private static final int DRINK = 1;

    private List<Pizza> mPizzas;
    private List<Drink> mDrinks;
    private Context mContext;

    private OnRemoveItemListener mListener;

    public CartAdapter(List<Pizza> pizzas, List<Drink> drinks, Context context) {
        this.mPizzas = pizzas;
        this.mDrinks = drinks;
        this.mContext = context;
        this.mListener = (OnRemoveItemListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_cart_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (getItemViewType(position) == PIZZA) {
            final Pizza pizza = mPizzas.get(position);

            holder.tvName.setText(pizza.getName());
            holder.tvPrice.setText(new PizzaManager().formatPrice(pizza.getTotalPrice()));

            holder.ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPizzas.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                    mListener.onRemovePizza();
                }
            });
        } else {
            final Drink drink = mDrinks.get(position - mPizzas.size());

            holder.tvName.setText(drink.getName());
            holder.tvPrice.setText(new DrinkManager().formatPrice(drink.getPrice()));

            holder.ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrinks.remove(holder.getAdapterPosition() - mPizzas.size());
                    notifyDataSetChanged();
                    mListener.onRemoveDrink();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPizzas.size() + mDrinks.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mPizzas.size()) {
            return PIZZA;
        }

        if (position - mPizzas.size() < mDrinks.size()) {
            return DRINK;
        }

        return -1;
    }

    public interface OnRemoveItemListener {
        void onRemovePizza();

        void onRemoveDrink();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRemove;
        TextView tvName;
        TextView tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ivRemove = itemView.findViewById(R.id.ivRemove);
            tvName = itemView.findViewById(R.id.tvTotal);
            tvPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }

}
