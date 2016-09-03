package com.anastatia.padc_mealorder.data.vos.responses;

import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by aung on 7/9/16.
 */
public class MealOrderListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("meal_list")
    private ArrayList<MealOrderVO> mealList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MealOrderVO> getMealOrderList() {
        return mealList;
    }
}
