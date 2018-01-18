package com.gft.menospizza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gft.menospizza.R;
import com.gft.menospizza.manager.IngredientsManager;
import com.gft.menospizza.model.Ingredient;

import java.util.List;

/**
 * Created by tiago on 17/01/18.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> mIngredients;
    private List<Integer> mIngredientsIds;
    private Context mContext;
    private OnIngredientChangedListener mListener;

    public IngredientsAdapter(List<Ingredient> ingredients, List<Integer> ingredientsIds, Context context) {
        this.mIngredients = ingredients;
        this.mIngredientsIds = ingredientsIds;
        this.mContext = context;
        this.mListener = (OnIngredientChangedListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Ingredient ingredient = mIngredients.get(position);

        holder.cbIngredient.setText(ingredient.getName());
        holder.tvPrice.setText(new IngredientsManager().formatPrice(ingredient.getPrice()));

        holder.cbIngredient.setChecked(new IngredientsManager().isDefaultIngredient(ingredient.getId(), mIngredientsIds));

        holder.cbIngredient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mListener.onAdd(ingredient);
                else
                    mListener.onRemove(ingredient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }


    public interface OnIngredientChangedListener {

        void onAdd(Ingredient ingredient);

        void onRemove(Ingredient ingredient);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbIngredient;
        TextView tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            cbIngredient = itemView.findViewById(R.id.tvTotal);
            tvPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }

}
