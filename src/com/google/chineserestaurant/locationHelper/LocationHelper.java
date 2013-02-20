package com.google.chineserestaurant.locationHelper;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
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

    /**
     * get lat and lng base on existed location
     */
    public static GeoPoint getGeoLocation(Context context, String location) {
        Geocoder geocoder = new Geocoder(context);
        GeoPoint point;
        try {
            List<Address> addressList = geocoder.getFromLocationName(location, 5);
            if (addressList != null && addressList.size() > 0) {
                point = new GeoPoint(
                    (int) (addressList.get(0).getLatitude() * 1E6), 
                    (int) (addressList.get(0).getLongitude() * 1E6));
                return point;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
