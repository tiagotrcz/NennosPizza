package com.gft.menospizza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gft.menospizza.R;
import com.gft.menospizza.model.Ingredient;

import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> mIngredients;
    private List<Integer> mInteger;
    private Context mContext;

    public IngredientsAdapter(List<Ingredient> mIngredients, List<Integer> mInteger, Context mContext) {
        this.mIngredients = mIngredients;
        this.mInteger = mInteger;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = null;

        View view = inflater.inflate(R.layout.item_list, parent, false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);

        holder.cbIngredient.setText(ingredient.getName());
        holder.tvPrice.setText(String.format("R$%s", ingredient.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbIngredient;
        TextView tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            cbIngredient = itemView.findViewById(R.id.cbIngredients);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

}
