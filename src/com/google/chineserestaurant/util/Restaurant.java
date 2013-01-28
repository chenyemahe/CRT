package com.google.chineserestaurant.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private String mRestaurantName;
    private String mRestaurantAddress;
    private String mIconUrl;
    private String mId;
    private int mPrice_Level;
    private String mRating;
    private String mReference;
    private String[] mType;
    private double mLat;
    private double mLng;
    private boolean mIsOpenNow;

    public String getName() {
        return (mRestaurantName);
    }

    public String getAddress() {
        return (mRestaurantAddress);
    }

    public double getLat() {
        return mLat;
    }

    public double getLng() {
        return mLng;
    }

    public String getId() {
        return mId;
    }

    public boolean getOpenNow() {
        return mIsOpenNow;
    }

    public String getIcon() {
        return mIconUrl;
    }

    public int getPriceLevel() {
        return mPrice_Level;
    }

    public String getRating() {
        return mRating;
    }

    public String getReference() {
        return mReference;
    }

    public String[] getType() {
        return mType;
    }

    public void setName(String name) {
        this.mRestaurantName = name;
    }

    public void setAddress(String address) {
        this.mRestaurantAddress = address;
    }

    public void setLat(String lat) {
        this.mLat = Double.parseDouble(lat);
    }

    public void setLng(String lng) {
        this.mLng = Double.parseDouble(lng);
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setOpenNow(String open) {
        boolean isOpen;
        if (open.equals("true")) {
            isOpen = true;
            this.mIsOpenNow = isOpen;
        }
        if (open.equals("false")) {
            isOpen = false;
            this.mIsOpenNow = isOpen;
        }
    }

    public void setIcon(String icon) {
        this.mIconUrl = icon;
    }

    public void setPriceLevel(int priceLevel) {
        this.mPrice_Level = priceLevel;
    }

    public void setRating(String rate) {
        this.mRating = rate;
    }

    public void setReference(String reference) {
        this.mReference = reference;
    }

    public void setType(String[] type) {
        this.mType = type;
    }

    public void setValue(String name, String address, String icon, String id, int priceLevel,
            String rating, String reference, String[] type, double lat, double lng, boolean isOpen) {
        this.mRestaurantName = name;
        this.mRestaurantAddress = address;
        this.mIconUrl = icon;
        this.mId = id;
        this.mPrice_Level = priceLevel;
        this.mRating = rating;
        this.mReference = reference;
        this.mType = type;
        this.mLat = lat;
        this.mLng = lng;
        this.mIsOpenNow = isOpen;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

    }
}
