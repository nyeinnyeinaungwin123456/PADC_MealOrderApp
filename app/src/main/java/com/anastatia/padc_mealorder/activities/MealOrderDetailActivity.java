package com.anastatia.padc_mealorder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anastatia.padc_mealorder.MealOrderApp;
import com.anastatia.padc_mealorder.R;
import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.anastatia.padc_mealorder.data.vos.agents.retrofit.RetrofitDataAgent;
import com.anastatia.padc_mealorder.dialogs.SharedDialog;
import com.anastatia.padc_mealorder.utils.MealOrderConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mm.technomation.mmtext.mmtext;

/**
 * Created by Nyein Nyein on 8/28/2016.
 */
public class MealOrderDetailActivity extends BaseActivity {

    private static final String IE_MEALORDER_NAME = "IE_MEALORDER_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_mealorder_desc)
    TextView tvMealOrderDesc;

    @BindView(R.id.iv_mealorder)
    ImageView ivMealOrder;

    @BindView(R.id.tv_price_desc)
    TextView tvPriceDesc;

    @BindView(R.id.tv_ingridients_desc)
    TextView tvIngridientsDesc;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    private MealOrderVO mMealOrder;

    public static Intent newIntent(String mealorderName) {
        Intent intent = new Intent(MealOrderApp.getContext(), MealOrderDetailActivity.class);
        intent.putExtra(IE_MEALORDER_NAME, mealorderName);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealorder_detail);
        ButterKnife.bind(this,this);

        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.IE_MEALORDER))
        mMealOrder = (MealOrderVO)intent.getSerializableExtra(MainActivity.IE_MEALORDER);

        bindData(mMealOrder);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            RetrofitDataAgent.getInstance().loadMealOrder();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GAUtils.getInstance().sendAppAction(GAUtils.ACTION_TAP_SHARE, mAttractionTitle);

                String imageUrl = mMealOrder.getImg_url();
                sendViaShareIntent(mMealOrder.getName() + " - " + imageUrl);
            }
        });

    }

    private void bindData(MealOrderVO mealOrderVO) {
        tvMealOrderDesc.setText(mealOrderVO.getDescription() + "\n\n"
                + mealOrderVO.getDescription());
       tvPriceDesc.setText("Price: "+mealOrderVO.getPrice());
//        tvIngridientsDesc.setText(mealOrderVO.getIngredients()+ "\n" + mealOrderVO.getIngredients());

        String imageUrl = mealOrderVO.getImg_url();
        Glide.with(ivMealOrder.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.meal)
                .error(R.drawable.meal)
                .into(ivMealOrder);


        collapsingToolbar.setTitle(mMealOrder.getName());

        StringBuilder builder = new StringBuilder();
        for(String s: mMealOrder.getIngredients()){
        builder.append(s);
            builder.append("\n");
        }

        tvIngridientsDesc.setText("Ingridients are: \n\n"+builder.toString().trim());

//        for(int i=0; i<mMealOrder.getIngredients().length; i++) {
////            int length =mMealOrder.getIngredients().length;
//            tvIngridientsDesc.setText(mMealOrder.getIngredients()[i]+"\n");
//        }
    }

    @OnClick(R.id.iv_locate_mealorder)
    public void onTapLocateAttraction(View view) {
//        GAUtils.getInstance().sendAppAction(GAUtils.ACTION_SHOW_ATTRACTION_ON_MAP,
//                mAttractionTitle);

        openLocationInMap(mMealOrder.getName());
    }

    @OnClick(R.id.iv_book_the_trip)
    public void onTapBookTheTrip(View view) {
//        GAUtils.getInstance().sendAppAction(GAUtils.ACTION_TAP_BOOK_ATTRACTION);

        String msg = getString(R.string.format_contact_option_confirmation, mMealOrder.getName());

        mmtext.isTextZawGyiProbably(msg);
        SharedDialog.confirmYesNoWithTheme(this, msg,
                getString(R.string.booking_phone), getString(R.string.booking_email), new SharedDialog.YesNoConfirmDelegate() {
                    @Override
                    public void onConfirmYes() {
//                        GAUtils.getInstance().sendAppAction(GAUtils.ACTION_BOOK_ATTRACTION_OVER_PHONE,
//                                mAttractionTitle);

                        makeCall(MealOrderConstants.CUSTOMER_SUPPORT_PHONE);
                    }

                    @Override
                    public void onConfirmNo() {
//                        GAUtils.getInstance().sendAppAction(GAUtils.ACTION_BOOK_ATTRACTION_BY_EMAIL,
//                                mAttractionTitle);

                        sendEmail(MealOrderConstants.CUSTOMER_SUPPORT_EMAIL, getString(R.string.book_the_trip_subject),
                                getString(R.string.format_book_the_trip_msg, mMealOrder.getName()));
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
