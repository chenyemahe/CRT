package com.google.chineserestaurant.locationHelper;

import android.content.Context;
import android.os.Handler;

public class LocationHelper {

	private Context context;
	private RestaurantFinder myRestaurantFinder;
	private Handler mHandler;
	
	public LocationHelper(Context context, Handler handler){
		this.context = context;
		this.myRestaurantFinder = new RestaurantFinder(context);
		this.mHandler = handler;
	}
	
	public void restaurantLocation(String location){
		myRestaurantFinder.findRestaurant(location, mHandler);
	}
	
}
