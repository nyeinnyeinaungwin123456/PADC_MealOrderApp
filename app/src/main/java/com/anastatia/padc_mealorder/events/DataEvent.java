package com.anastatia.padc_mealorder.events;

import com.anastatia.padc_mealorder.data.vos.MealOrderVO;

import java.util.List;

/**
 * Created by aung on 7/9/16.
 */
public class DataEvent {
    public static class AttractionDataLoadedEvent {

        private String extraMessage;
        private List<MealOrderVO> mealorderList;

        public AttractionDataLoadedEvent(String extraMessage, List<MealOrderVO> mealorderList) {
            this.extraMessage = extraMessage;
            this.mealorderList = mealorderList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MealOrderVO> getMealOrderList() {
            return mealorderList;
        }
    }

    public static class DatePickedEvent {
        private String dateOfBrith;

        public DatePickedEvent(String dateOfBrith) {
            this.dateOfBrith = dateOfBrith;
        }

        public String getDateOfBrith() {
            return dateOfBrith;
        }
    }

    public static class RefreshUserLoginStatusEvent {

    }
}
