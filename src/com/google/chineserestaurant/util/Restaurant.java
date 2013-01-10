package com.google.chineserestaurant.util;
public class Restaurant {

    private String mRestaurantName;
    private String mRestaurantAddress;

    public String getName() {
        return (mRestaurantName);
    }

    public void setName(String name) {
        this.mRestaurantName = name;
    }

    public String getAddress() {
        return (mRestaurantAddress);
    }

    public void setAddress(String address) {
        this.mRestaurantAddress = address;
    }

    public void setValue(String name, String address) {
        this.mRestaurantName = name;
        this.mRestaurantAddress = address;
    }
}
