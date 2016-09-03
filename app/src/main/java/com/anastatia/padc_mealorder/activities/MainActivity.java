package com.anastatia.padc_mealorder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.anastatia.padc_mealorder.MealOrderApp;
import com.anastatia.padc_mealorder.R;
import com.anastatia.padc_mealorder.data.vos.MealOrderVO;
import com.anastatia.padc_mealorder.fragments.MealOrderFragment;
import com.anastatia.padc_mealorder.views.holders.MealOrderViewHolder;

public class MainActivity extends BaseActivity implements MealOrderViewHolder.ControllerMealOrderItem{

    public static final String IE_MEALORDER="Mealorder";

    public static Intent newIntent(){
        Intent intent = new Intent(MealOrderApp.getContext(), MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fl_container, MealOrderFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapMealOrder(MealOrderVO mealOrderVO, ImageView ivMealOrder) {
        Intent intent = MealOrderDetailActivity.newIntent(mealOrderVO.getName());
        intent.putExtra(IE_MEALORDER, mealOrderVO);
        startActivity(intent);
    }
}
