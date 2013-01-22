package com.google.chineserestaurant.util;

import java.io.Serializable;

public class Restaurant implements Serializable{
    
    private static final long serialVersionUID = 5738159996481030557L;
    private String mRestaurantName;
    private String mRestaurantAddress;
    private double lat;
    private double lng;

    public String getName() {
        return (mRestaurantName);
    }

    public String getAddress() {
        return (mRestaurantAddress);
    }
    
    public double getLat(){
        return lat;
    }

    public double getLng(){
        return lng;
    }

    public void setName(String name) {
        this.mRestaurantName = name;
    }

    public void setAddress(String address) {
        this.mRestaurantAddress = address;
    }

    public void setValue(String name, String address) {
        this.mRestaurantName = name;
        this.mRestaurantAddress = address;
    }
    
    public void setLat(String lat){
        this.lat = Double.parseDouble(lat);
    }
    
    public void setLng(String lng){
        this.lng = Double.parseDouble(lng);
    }
}
