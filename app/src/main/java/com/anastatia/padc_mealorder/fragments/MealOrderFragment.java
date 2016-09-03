package com.anastatia.padc_mealorder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anastatia.padc_mealorder.R;
import com.anastatia.padc_mealorder.adapters.MealOrderAdapter;
import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.anastatia.padc_mealorder.data.vos.agents.retrofit.MealOrderApi;
import com.anastatia.padc_mealorder.data.vos.agents.retrofit.RetrofitDataAgent;
import com.anastatia.padc_mealorder.data.vos.models.MealOrderModel;
import com.anastatia.padc_mealorder.events.DataEvent;
import com.anastatia.padc_mealorder.views.holders.MealOrderViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * Created by Nyein Nyein on 8/27/2016.
 */
public class MealOrderFragment extends Fragment {

    @BindView(R.id.rv_mealorders)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
            SwipeRefreshLayout refreshlayout;

    MealOrderAdapter mealorderAdapter;
    private MealOrderViewHolder.ControllerMealOrderItem mControllerMealOrderItem;

    MealOrderApi mealOrderApi;

    public MealOrderFragment() {
    }

    public static MealOrderFragment newInstance(){
        MealOrderFragment mealOrderFragment = new MealOrderFragment();
        return mealOrderFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerMealOrderItem = (MealOrderViewHolder.ControllerMealOrderItem)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mealorder, container, false);
        ButterKnife.bind(this,view);

        List<MealOrderVO> mealorderList = MealOrderModel.getInstance().getAttractionList();
        mealorderAdapter = new MealOrderAdapter(mealorderList, mControllerMealOrderItem);

        recyclerView.setAdapter(mealorderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);

        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RetrofitDataAgent.getInstance().loadMealOrder();
            }
        });

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    public void onEventMainThread(DataEvent.AttractionDataLoadedEvent event) {
        refreshlayout.setRefreshing(false);
        String extra = event.getExtraMessage();
//        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<MealOrderVO> newMealOrderList = event.getMealOrderList();
        mealorderAdapter.setNewData(newMealOrderList);
    }
}
