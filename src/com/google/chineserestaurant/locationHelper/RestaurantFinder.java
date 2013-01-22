package com.google.chineserestaurant.locationHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.chineserestaurant.httpHelper.HttpAdapter;
import com.google.chineserestaurant.jsonHelper.JsonAdapter;
import com.google.chineserestaurant.util.Restaurant;
import com.google.chineserestaurant.util.Util;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class RestaurantFinder {

    private final static String placeSearch_json = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    private final static String placeSearch_xml = "https://maps.googleapis.com/maps/api/place/textsearch/xml?query=";
    private final static String api_key = "&key=AIzaSyCAgUdgdGM2_2k96IwK2uhGXcnpGD5Pu6E";
    private final static String search_type = "chinese restaurants+in+";
    private final static String sensor_true = "&sensor=true";

    private String location;
    private Context context;
    private HttpAdapter mHttpAdatper;
    private JsonAdapter mJsonAdapter;
    private ArrayList<Restaurant> mRestaurantList;
    private Handler mHandler;

    RestaurantFinder(Context context) {
        this.location = "";
        this.context = context;
        mHttpAdatper = new HttpAdapter();
        mJsonAdapter = new JsonAdapter();
        mRestaurantList = new ArrayList<Restaurant>();
    }

    private void setLocation(String location, Handler handler) {
        this.location = location;
        this.mHandler = handler;
    }

    public void findRestaurant(String l, Handler handler) {
        setLocation(l, handler);
        new Thread(new httpConnect()).start();
    }

    public static String convertURL(String str) {

        String url = null;
        try {
            url = new String(str.trim().replace(" ", "%20"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    private class httpConnect implements Runnable {

        JSONArray jsonArray;
        JSONObject jsonObject;
        JSONObject jsonObjectGeo;
        JSONObject jsonObjectLocation;

        @Override
        public void run() {
            String url = convertURL(placeSearch_json + search_type + location + sensor_true + api_key);
            String strResult = "";

            strResult = mHttpAdatper.httpGet(url);

            if (strResult == null) {
                Toast.makeText(context, "Can't get result!", Toast.LENGTH_SHORT).show();
                return;
            }
            setRestaurantList(strResult);
            Message msg = prepareMessage(mRestaurantList);
            mHandler.sendMessage(msg);
        }

        /**
         * set search result to restaurant list
         * 
         * @param result
         */
        private void setRestaurantList(String result) {
            jsonArray = mJsonAdapter.getArrayFromJSON(result, "results");
            for (int i = 0; i < mJsonAdapter.getNoOfElements(jsonArray); i++) {
                Restaurant restaurant = new Restaurant();
                try {
                    jsonObject = jsonArray.getJSONObject(i);
                    jsonObjectGeo = jsonObject.getJSONObject("geometry");
                    jsonObjectLocation = jsonObjectGeo.getJSONObject("location");
                    restaurant.setLat(jsonObjectLocation.getString("lat"));
                    restaurant.setLng(jsonObjectLocation.getString("lng"));
                    mRestaurantList.add(restaurant);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private Message prepareMessage(Object meg) {
        Message result = mHandler.obtainMessage(Util.Search_Result_Done, meg);
        return result;
    }
}
