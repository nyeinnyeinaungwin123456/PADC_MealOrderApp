package com.anastatia.padc_mealorder.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anastatia.padc_mealorder.R;
import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aung on 7/6/16.
 */
public class MealOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_meal_name)
    TextView tvMealOrderName;

    @BindView(R.id.iv_mealorder)
    ImageView ivMealOrder;

    @BindView(R.id.tv_mealorder_desc)
    TextView tvMealOrderDesc;

//    @BindView(R.id.tv_price)
//    TextView tvPrice;
//
//    @BindView(R.id.tv_ingredients)
//    TextView tvIngredients;

    private ControllerMealOrderItem mController;
    private MealOrderVO mMealOrder;

    public MealOrderViewHolder(View itemView, ControllerMealOrderItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(MealOrderVO mealorder) {
        mMealOrder = mealorder;
        tvMealOrderName.setText(mealorder.getName());
        tvMealOrderDesc.setText(mealorder.getDescription());

        String imageUrl = mealorder.getImg_url();

        Glide.with(ivMealOrder.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.meal)
                .error(R.drawable.meal)
                .into(ivMealOrder);

//        tvPrice.setText(mealorder.getPrice());

    }

    @Override
    public void onClick(View view) {
        mController.onTapMealOrder(mMealOrder, ivMealOrder);
    }

    public interface ControllerMealOrderItem {
        void onTapMealOrder(MealOrderVO mealOrderVO, ImageView ivMealOrder);
    }
}
