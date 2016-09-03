package com.anastatia.padc_mealorder.data.vos.agents.retrofit;

import com.anastatia.padc_mealorder.data.vos.responses.MealOrderListResponse;
import com.anastatia.padc_mealorder.utils.MealOrderConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aung on 7/9/16.
 */
public interface MealOrderApi {

    @FormUrlEncoded
    @POST(MealOrderConstants.API_GET_MEALORDER_LIST)
    Call<MealOrderListResponse> loadAttractions(
            @Field(MealOrderConstants.PARAM_ACCESS_TOKEN) String param);


}
