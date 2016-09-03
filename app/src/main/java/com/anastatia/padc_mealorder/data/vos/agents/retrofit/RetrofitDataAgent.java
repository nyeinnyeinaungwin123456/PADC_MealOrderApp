package com.anastatia.padc_mealorder.data.vos.agents.retrofit;

import com.anastatia.padc_mealorder.data.vos.agents.MealOrderDataAgent;
import com.anastatia.padc_mealorder.data.vos.models.MealOrderModel;
import com.anastatia.padc_mealorder.data.vos.responses.MealOrderListResponse;
import com.anastatia.padc_mealorder.utils.CommonInstances;
import com.anastatia.padc_mealorder.utils.MealOrderConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 7/9/16.
 */
public class RetrofitDataAgent implements MealOrderDataAgent {

    private static RetrofitDataAgent objInstance;

    private final MealOrderApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MealOrderConstants.MEALORDER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(MealOrderApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadMealOrder() {
        Call<MealOrderListResponse> loadAttractionCall = theApi.loadAttractions(MealOrderConstants.ACCESS_TOKEN);
        loadAttractionCall.enqueue(new Callback<MealOrderListResponse>() {
            @Override
            public void onResponse(Call<MealOrderListResponse> call, Response<MealOrderListResponse> response) {
                MealOrderListResponse attractionListResponse = response.body();
                    if (attractionListResponse == null) {
                    MealOrderModel.getInstance().notifyErrorInLoadingAttractions(response.message());
                } else {
                    MealOrderModel.getInstance().notifyAttractionsLoaded(attractionListResponse.getMealOrderList());
                }
            }

            @Override
            public void onFailure(Call<MealOrderListResponse> call, Throwable throwable) {
                MealOrderModel.getInstance().notifyErrorInLoadingAttractions(throwable.getMessage());
            }
        });
    }


}
