package com.anastatia.padc_mealorder;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nyein Nyein on 8/27/2016.
 */
public class MealOrderApp extends Application {

    public static final String TAG = "MealOrderApp";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getContext() {

        return context;
    }

}
