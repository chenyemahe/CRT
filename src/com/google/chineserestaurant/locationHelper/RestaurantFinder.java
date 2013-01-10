package com.google.chineserestaurant.locationHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

public class RestaurantFinder{

	private final static String placeSearch_json = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
	private final static String placeSearch_xml = "https://maps.googleapis.com/maps/api/place/textsearch/xml?query=";
	private final static String api_key = "&key=AIzaSyCAgUdgdGM2_2k96IwK2uhGXcnpGD5Pu6E";
	private final static String search_type = "chinese restaurants+in+";
	private final static String sensor_true = "&sensor=true";
	
	private String location;
	private Context context;
	
	RestaurantFinder(Context context){
		this.location = "";
		this.context = context;
	}
	
	private void setLocation(String location){
		this.location = location;
	}
	
	public void findRestaurant(String l){
		setLocation(l);
		new Thread(new httpConnect()).start();
	}
	
	public static String convertURL(String str) {

	    String url = null;
	    try{
	    url = new String(str.trim().replace(" ", "%20"));
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return url;
	}
	
	private class httpConnect implements Runnable{

		@Override
		public void run() {
			String url = convertURL(placeSearch_json + search_type + location + sensor_true + api_key);
			JSONArray strResult = null;

			HttpGet get = new HttpGet(url);
			

			try {
				HttpParams httpParameters = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
				HttpClient httpClient = new DefaultHttpClient(httpParameters);

				HttpResponse httpResponse = null;
				httpResponse = httpClient.execute(get);

				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					strResult = (JSONArray) httpResponse.getEntity();
				}
			} catch (Exception e) {
				return;
			}

			if (strResult == null) {
				Toast.makeText(context, "Can't get result!", Toast.LENGTH_SHORT).show();
				return;
			}else{
				
			}
			
		}
		
	}

}
