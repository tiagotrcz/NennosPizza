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
import com.gft.menospizza.model.Drink;

import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    private List<Drink> mDrinks;
    private Context mContext;
    private OnAddDrinkListener mListener;

    public DrinksAdapter(List<Drink> drinks, Context context) {
        this.mDrinks = drinks;
        this.mContext = context;
        this.mListener = (OnAddDrinkListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_drink_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Drink drink = mDrinks.get(position);

        holder.tvName.setText(drink.getName());
        holder.tvPrice.setText(new DrinkManager().formatPrice(drink.getPrice()));

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAdd(drink);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDrinks.size();
    }

    public interface OnAddDrinkListener {
        void onAdd(Drink drink);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAdd;
        TextView tvName;
        TextView tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ivAdd = itemView.findViewById(R.id.ivAdd);
            tvName = itemView.findViewById(R.id.tvTotal);
            tvPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }

}
