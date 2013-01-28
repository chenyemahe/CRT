package com.google.chineserestaurant;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.chineserestaurant.locationHelper.PointItemizedOverlay;
import com.google.chineserestaurant.util.Restaurant;
import com.google.chineserestaurant.util.Util;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MapViewActivity extends MapActivity implements LocationListener, OnClickListener {

    private ArrayList<Restaurant> mRestaurantList;
    private MapController mapController;
    private MapView mapView;
    private List<Overlay> mapOverlays;
    private PointItemizedOverlay itemizedOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview_activity);

        mRestaurantList = getIntent().getExtras().getParcelableArrayList(Util.Restaurant_Node_List);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        itemizedOverlay = new PointItemizedOverlay(getResources().getDrawable(R.drawable.restaurant_71));

        mapOverlays = mapView.getOverlays();
        // coords = getUserLocation();

        // change to microdegrees in GeoPoint
        // point = new GeoPoint((int) (coords[0] * 1e6), (int) (coords[1] *
        // 1e6));
        // ownerItemizedOverlay = addUserImage(myDrawable, point);

        addRestaurant();

        mapView.invalidate();
    }

    private void addRestaurant() {
        GeoPoint geoPoint;
        for (Restaurant restaurant : mRestaurantList) {
            geoPoint = new GeoPoint((int) (restaurant.getLat() * 1e6), (int) (restaurant.getLng() * 1e6));
            addPointImage(geoPoint);
        }
    }

    /**
     * add point on map
     * 
     * @param myDrawable
     * @param myPoint
     */
    private void addPointImage(GeoPoint myPoint) {
        OverlayItem overlayitem = new OverlayItem(myPoint, "", "");
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

}
