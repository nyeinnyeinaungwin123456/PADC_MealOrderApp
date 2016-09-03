package com.anastatia.padc_mealorder.data.vos.models;

import com.anastatia.padc_mealorder.data.vos.agents.MealOrderDataAgent;
import com.anastatia.padc_mealorder.data.vos.agents.retrofit.RetrofitDataAgent;

import de.greenrobot.event.EventBus;


/**
 * Created by aung on 7/15/16.
 */
public abstract class BaseModel {

    private static final int INIT_DATA_AGENT_RETROFIT = 1;

    protected MealOrderDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        switch (initType) {
               case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }
    }

    public void onEventMainThread(Object obj) {

    }
}
