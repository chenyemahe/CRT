package com.google.chineserestaurant;

import java.util.ArrayList;

import com.google.chineserestaurant.util.Restaurant;
import com.google.chineserestaurant.util.Util;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SeResultListViewActivity extends ListActivity {

    private ListView mListView;
    private ArrayList<Restaurant> mRestaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.se_result_list_activity);
        mListView = (ListView) findViewById(android.R.id.list);
        mRestaurantList = getIntent().getExtras().getParcelableArrayList(
                Util.Intent_Restaurant_Node_List);
        mListView.setAdapter(new RestaurantAdapter());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ResInfoActivity.class);
        intent.putExtra(Util.Intent_Restaurant_Node, mRestaurantList.get(position));
        startActivity(intent);
    }

    class RestaurantAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;

        RestaurantAdapter() {
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
            RestaurantViewHolder holder = null;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.list_view_result, null);
                holder = new RestaurantViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (RestaurantViewHolder) convertView.getTag();
            }
            holder.setView(position);
            return convertView;
        }
    }

    class RestaurantViewHolder {

        private TextView mName;
        private TextView mPriceLevel;
        private TextView mIsOpen;
        private RatingBar mRating;
        private TextView mRateingText;

        RestaurantViewHolder(View view) {
            mName = (TextView) view.findViewById(R.id.tv_restaurant_name);
            mPriceLevel = (TextView) view.findViewById(R.id.tv_restaurant_price_rate);
            mIsOpen = (TextView) view.findViewById(R.id.tv_restaurant_is_open);
            mRating = (RatingBar) view.findViewById(R.id.rb_restaurant_rating);
            mRateingText = (TextView) view.findViewById(R.id.tv_restaurant_rating);
        }

        public void setView(int index) {
            Restaurant r = mRestaurantList.get(index);
            String rName = "Name: " + r.getName();
            String rPriceLevel = "Price Rating: \n";
            String rIsOpen = r.getOpenNow();
            int pLevel = r.getPriceLevel();
            for (int i = 0; i < pLevel; i++) {
                rPriceLevel += "$";
            }
            mName.setText(rName);
            if (pLevel != Integer.MIN_VALUE) {
                mPriceLevel.setText(rPriceLevel);
            }
            if (rIsOpen != null) {
                boolean isOpen = r.stringToBoolean(rIsOpen);
                if (isOpen) {
                    mIsOpen.setText("Open Status: \n" + "Open Now");
                } else {
                    mIsOpen.setText("Open Status: " + "Close");
                }
            }
            if (r.getRating() != null) {
                mRating.setRating(Float.parseFloat(r.getRating()));
                mRating.setVisibility(View.VISIBLE);
                mRateingText.setVisibility(View.VISIBLE);
            }else{
                mRating.setVisibility(View.GONE);
                mRateingText.setVisibility(View.GONE);
            }
        }
    }
}
