package com.google.chineserestaurant.locationHelper;

import android.content.Context;

public class LocationHelper {

	private Context context;
	private RestaurantFinder myRestaurantFinder;
	
	public LocationHelper(Context context){
		this.context = context;
		this.myRestaurantFinder = new RestaurantFinder(context);
	}
	
	public void restaurantLocation(String location){
		myRestaurantFinder.findRestaurant(location);
	}
	
}
