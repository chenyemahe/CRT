package com.google.chineserestaurant.jsonHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JsonAdapter {
    public String getFromJSON(String data, String key) {
        String retValue = null;
        try {
            JSONObject json = new JSONObject(data);
            retValue = json.getString(key);
        } catch (Exception e) {
            Log.i("Json Exception", e.getMessage());
        }
        return retValue;
    }

    public JSONArray getArrayFromJSON(String data, String key) {
        JSONObject json = null;
        JSONArray json_array = null;
        try {
            json = new JSONObject(data);
            json_array = json.optJSONArray(key);
        } catch (Exception e) {
            Log.i("Json Exception", e.getMessage());
        }
        return json_array;
    }

    public int getNoOfElements(String data, String key) {
        JSONObject json = null;
        JSONArray json_array = null;
        int length = 0;
        try {
            json = new JSONObject(data);
            json_array = json.optJSONArray(key);
            length = json_array.length();
        } catch (Exception e) {
            Log.i("Json Exception", e.getMessage());
        }
        return length;
    }
    
    public int getNoOfElements(JSONArray json_array) {
        int length = 0;
        try {
            length = json_array.length();
        } catch (Exception e) {
            Log.i("Json Exception", e.getMessage());
        }
        return length;
    }
    
    public JSONObject createJson(JSONObject jObj, String key, String content) {
        try {
            jObj.put(key, content);
        } catch (Exception e) {
            Log.i("Json Exception", e.getMessage());
        }
        return jObj;
    }

}
