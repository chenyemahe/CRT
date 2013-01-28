package com.google.chineserestaurant;

import java.util.ArrayList;

import com.google.chineserestaurant.util.Restaurant;
import com.google.chineserestaurant.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SeResultListViewActivity extends Activity {

    private ListView mListView;
    private ArrayList<Restaurant> mRestaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.se_result_list_activity);
        mListView = (ListView) findViewById(R.id.maplist_restaurants);
        mRestaurantList = getIntent().getExtras().getParcelableArrayList(Util.Restaurant_Node_List);
        mListView.setAdapter(new RestaurantAdapter());
    }

    class RestaurantAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        
        RestaurantAdapter(){
            mLayoutInflater = getLayoutInflater();
        }
        
        @Override
        public int getCount() {
            return mRestaurantList.size();
        }

        @Override
        public Object getItem(int index) {
            return mRestaurantList.get(index);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.list_view_result, null);
            }
            return convertView;
        }
    }
    
    
}
