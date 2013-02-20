package com.google.chineserestaurant.locationHelper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.chineserestaurant.httpHelper.HttpAdapter;
import com.google.chineserestaurant.jsonHelper.JsonAdapter;
import com.google.chineserestaurant.util.Restaurant;
import com.google.chineserestaurant.util.Util;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class RestaurantFinder {

    private String location;
    // private String geoLocation;
    private Context context;
    private HttpAdapter mHttpAdatper;
    private JsonAdapter mJsonAdapter;
    private Handler mHandler;

    RestaurantFinder(Context context) {
        this.location = "";
        this.context = context;
        mHttpAdatper = new HttpAdapter();
        mJsonAdapter = new JsonAdapter();
    }

    private void setLocation(String location, Handler handler) {
        this.location = location;
        this.mHandler = handler;
    }

    public void findRestaurant(String l, Handler handler) {
        setLocation(l, handler);
        new Thread(new httpConnect()).start();
    }

    private class httpConnect implements Runnable {
        private ArrayList<Restaurant> mRestaurantList = new ArrayList<Restaurant>();
        String pageToken;

        @Override
        public void run() {
            // setGeoLocation();
            // if (geoLocation != null) {
            // startGoogleRadarSearch();
            String url = mHttpAdatper.convertURL(Util.Place_Text_Search_Json
                    + Util.Place_Text_Search_Query + location + Util.Place_Sensor_True
                    + Util.Place_Api_Key);
            setRestaurantList(startGoogleTextSearch(url));
            sleep();
            while (pageToken != null) {
                String url_next_page = mHttpAdatper.convertURL(Util.Place_Text_Search_Json
                        + Util.Place_Text_Search_Query + location + Util.Place_Page_Token + pageToken
                        + Util.Place_Sensor_True + Util.Place_Api_Key);
                setRestaurantList(startGoogleTextSearch(url_next_page));
                sleep();
            }
            Message msg = prepareMessage(mRestaurantList);
            mHandler.sendMessage(msg);
            // }
        }

        /**
         * start google radar search
         */
        /**
         * private void startGoogleRadarSearch() { String url =
         * mHttpAdatper.convertURL(Util.Place_Radar_Search_Json + "location=" +
         * geoLocation + "&radius=" + radius + Util.Place_Radar_Search_Name +
         * Util.Place_Sensor_True + Util.Place_Api_Key); String strResult = "";
         * 
         * strResult = mHttpAdatper.httpGet(url);
         * 
         * if (strResult == null) { Toast.makeText(context, "Can't get result!",
         * Toast.LENGTH_SHORT).show(); return; } }
         */

        /**
         * start google text search
         */
        private String startGoogleTextSearch(String url) {

            String strResult = "";

            strResult = mHttpAdatper.httpGet(url);

            if (strResult == null) {
                Toast.makeText(context, "Can't get result!", Toast.LENGTH_SHORT).show();
                return null;
            }

            pageToken = mJsonAdapter.getString(strResult, Util.Place_Request_Next_Page_Token);
            return strResult;
        }

        /**
         * set search result to restaurant list
         * 
         * @param result
         */
        private synchronized void setRestaurantList(String result) {
            JSONArray jsonArray;
            jsonArray = mJsonAdapter.getJSONArray(result, Util.Place_Request_Result_Results);
            for (int i = 0; i < mJsonAdapter.getNoOfElements(jsonArray); i++) {
                setRestaurantNode(jsonArray, i);
            }
        }

        /**
         * set restaurant info to restaurant node
         * 
         * @param jsonArray
         *            restaurant info array
         * @param index
         *            index of the restaurant
         */
        private void setRestaurantNode(JSONArray jsonArray, int index) {
            Restaurant restaurant = new Restaurant();
            JSONObject jsonObject;
            JSONObject jsonObjectLocation;
            JSONArray tempArray;
            try {
                jsonObject = jsonArray.getJSONObject(index);
                jsonObjectLocation = mJsonAdapter.getJSONObject(
                        mJsonAdapter.getJSONObject(jsonObject, Util.Place_Request_Result_Geometry),
                        Util.Place_Request_Result_Location);
                restaurant.setLat(mJsonAdapter.getString(jsonObjectLocation,
                        Util.Place_Request_Result_Lat));
                restaurant.setLng(mJsonAdapter.getString(jsonObjectLocation,
                        Util.Place_Request_Result_Lng));
                restaurant.setAddress(mJsonAdapter.getString(jsonObject,
                        Util.Place_Request_Result_Formatted_Address));
                restaurant.setIcon(mJsonAdapter.getString(jsonObject, Util.Place_Request_Result_Icon));
                restaurant.setId(mJsonAdapter.getString(jsonObject, Util.Place_Request_Result_Id));
                restaurant.setName(mJsonAdapter.getString(jsonObject, Util.Place_Request_Result_Name));
                String openNow = mJsonAdapter.getBoolean(
                        mJsonAdapter.getJSONObject(jsonObject, Util.Place_Request_Result_Opening_Hours),
                        Util.Place_Request_Result_Open_Now);
                if (openNow != null)
                    restaurant.setOpenNow(openNow);
                restaurant.setPriceLevel(mJsonAdapter.getInt(jsonObject,
                        Util.Place_Request_Result_Price_Level));
                restaurant.setRating(mJsonAdapter
                        .getString(jsonObject, Util.Place_Request_Result_Rating));
                restaurant.setReference(mJsonAdapter.getString(jsonObject,
                        Util.Place_Request_Result_Reference));

                tempArray = mJsonAdapter.getJSONArray(jsonObject, Util.Place_Request_Result_Type);
                restaurant.setType(mJsonAdapter.jsonArrayToStringArray(tempArray));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mRestaurantList.add(restaurant);
        }

        private Message prepareMessage(Object meg) {
            Message result = mHandler.obtainMessage(Util.Search_Result_Done, meg);
            return result;
        }

        private void sleep() {
            try {
                Thread.sleep(Util.Thread_Sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
