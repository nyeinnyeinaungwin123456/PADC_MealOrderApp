package com.anastatia.padc_mealorder.data.vos.models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.anastatia.padc_mealorder.MealOrderApp;
import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.anastatia.padc_mealorder.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Nyein Nyein on 7/18/2016.
 */
public class MealOrderModel extends BaseModel{

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";


    public List<MealOrderVO> mealorderVOList;
    private static MealOrderModel mealorderModel;
//    public static final String DUMMY_ATTRACTION_LIST = "myanmar_attractions.json";

    private MealOrderModel(){
        super();
        mealorderVOList = new ArrayList<>();
        dataAgent.loadMealOrder();
    }


    public static MealOrderModel getInstance() {

    if(mealorderModel==null) {
        mealorderModel = new MealOrderModel();
    }
        return mealorderModel;
    }

    public List<MealOrderVO> getAttractionList() {

        return mealorderVOList;

    }

    public MealOrderVO getMealOrderByName(String mealorderName) {
        for (MealOrderVO mealorderVO : mealorderVOList) {
            if (mealorderVO.getName().equals(mealorderName)) {
                return mealorderVO;
            }
        }
        return null;
    }

    public void notifyErrorInLoadingAttractions(String message) {

    }

    public void notifyAttractionsLoaded(List<MealOrderVO> mealorderList) {
        //Notify that the data is ready - using LocalBroadcast
        mealorderVOList = mealorderList;
        broadcastAttractionLoadedWithEventBus();
        //keep the data in persistent layer.
//        MealOrderVO.saveAttractions(mAttractionList);

    }

//        private List<MealOrderVO> initializeMealOrderList(){
//
//        List<MealOrderVO> mealorderList = new ArrayList<>();
//
//        try{
//            String dummyAttractiontList = JsonUtils.getInstance().loadDummyData(DUMMY_ATTRACTION_LIST);
//            Type listType = new TypeToken<List<AttractionVO>>() {
//            }.getType();
//
//            attractList = CommonInstances.getGsonInstance().fromJson(dummyAttractiontList, listType);
//        }catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return attractList;
//    }

    private void broadcastAttractionLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(MealOrderApp.getContext()).sendBroadcast(intent);
    }

    private void broadcastAttractionLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.AttractionDataLoadedEvent("extra-in-broadcast", mealorderVOList));
    }

    public List<MealOrderVO> getMealOrderVOList(){
        return mealorderVOList;
    }


}
