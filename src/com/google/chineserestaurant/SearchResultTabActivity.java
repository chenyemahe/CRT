package com.google.chineserestaurant;

import java.util.ArrayList;

import com.google.chineserestaurant.locationHelper.LocationHelper;
import com.google.chineserestaurant.util.Restaurant;
import com.google.chineserestaurant.util.Util;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class SearchResultTabActivity extends TabActivity {

    private static String zip;
    private String city;
    private LocationHelper mLocationHelper;
    private static TabHost mTabHost;
    private static TabActivity mActivity;

    private static Handler mHandler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == Util.Search_Result_Done) {
                mRestaurantList = (ArrayList<Restaurant>) msg.obj;
                Intent mapIntent1 = new Intent(mActivity, MapViewActivity.class);
                mapIntent1.putExtra(Util.Intent_Restaurant_Node_List, mRestaurantList);
                mapIntent1.putExtra(Util.Intent_Location, zip);
                mTabHost.addTab(mTabHost.newTabSpec("Map").setIndicator("Map")
                        .setContent(mapIntent1));
                Intent listIntent = new Intent(mActivity, SeResultListViewActivity.class);
                listIntent.putExtra(Util.Intent_Restaurant_Node_List, mRestaurantList);
                mTabHost.addTab(mTabHost.newTabSpec("Result List").setIndicator("Result List")
                        .setContent(listIntent));
                mTabHost.setCurrentTab(0);
            }
        }
    };
    private static ArrayList<Restaurant> mRestaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_search_result);
        mActivity = this;
        mLocationHelper = new LocationHelper(getApplicationContext(), mHandler);
        zip = getIntent().getStringExtra(Util.Intent_Zip_Code);
        city =getIntent().getStringExtra(Util.Intent_City_Name);

        mLocationHelper.restaurantLocation(zip);
        mTabHost = getTabHost();
    }
}
