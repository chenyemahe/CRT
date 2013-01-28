package com.google.chineserestaurant.jsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonAdapter {
    private final static String TAG ="JSON: ";
    public String getString(String data, String key) {
        String retValue = null;
        try {
            JSONObject json = new JSONObject(data);
            retValue = json.getString(key);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return retValue;
    }

    public String getString(JSONObject json, String key) {
        String retValue = null;
        try {
            retValue = json.getString(key);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return retValue;
    }
    
    public String getBoolean(JSONObject json, String key) {
        String retValue = null;
        try {
            retValue = json.getString(key);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return retValue;
    }

    public int getInt(JSONObject json, String key) {
        int retValue = Integer.MIN_VALUE;
        try {
            retValue = json.getInt(key);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return retValue;
    }

    public JSONObject getJSONObject(JSONObject json, String key) {
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(key);
        } catch (JSONException e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return jsonObject;
    }

    public JSONArray getJSONArray(String data, String key) {
        JSONObject json = null;
        JSONArray json_array = null;
        try {
            json = new JSONObject(data);
            json_array = json.optJSONArray(key);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return json_array;
    }

    public JSONArray getJSONArray(JSONObject json, String key) {
        JSONArray json_array = null;
        try {
            json_array = json.optJSONArray(key);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
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
            Log.d(TAG, "Json Exception" + key);
        }
        return length;
    }

    public int getNoOfElements(JSONArray json_array) {
        int length = 0;
        try {
            length = json_array.length();
        } catch (Exception e) {
            Log.d(TAG, "Json Exception");
        }
        return length;
    }

    public JSONObject createJson(JSONObject jObj, String key, String content) {
        try {
            jObj.put(key, content);
        } catch (Exception e) {
            Log.d(TAG, "Json Exception" + key);
        }
        return jObj;
    }

    public String[] jsonArrayToStringArray(JSONArray tempArray) {
        String[] type;
        type = new String[tempArray.length()];
        try {
            for (int i = 0; i < type.length; i++) {
                type[i] = tempArray.getString(i);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return type;
    }

}
