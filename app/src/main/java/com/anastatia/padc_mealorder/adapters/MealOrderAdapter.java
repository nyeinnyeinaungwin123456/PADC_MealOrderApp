package com.anastatia.padc_mealorder.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anastatia.padc_mealorder.MealOrderApp;
import com.anastatia.padc_mealorder.R;
import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.anastatia.padc_mealorder.views.holders.MealOrderViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nyein Nyein on 7/18/2016.
 */
public class MealOrderAdapter extends RecyclerView.Adapter<MealOrderViewHolder> {

    private LayoutInflater layoutInflater;
    private List<MealOrderVO> mMealOrderVOList = new ArrayList<>();
    private MealOrderViewHolder.ControllerMealOrderItem mControllerMealOrderItem;

    public MealOrderAdapter(List<MealOrderVO> mealorderVOList, MealOrderViewHolder.ControllerMealOrderItem controllerMealOrderItem) {
        layoutInflater = LayoutInflater.from(MealOrderApp.getContext());
        mMealOrderVOList = new ArrayList<>();;
        mControllerMealOrderItem = controllerMealOrderItem;
    }


    @Override
    public MealOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= layoutInflater.inflate(R.layout.list_row_order, parent, false);
        return new MealOrderViewHolder(view, mControllerMealOrderItem);

    }

    @Override
    public void onBindViewHolder(MealOrderViewHolder holder, int position) {

        holder.bindData(mMealOrderVOList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mMealOrderVOList == null) {
            return 0;
        }
        return mMealOrderVOList.size();
    }

    public void setNewData(List<MealOrderVO> newMealOrderList) {
        mMealOrderVOList.clear();
        mMealOrderVOList.addAll(newMealOrderList);
        notifyDataSetChanged();
    }
}
