package com.google.chineserestaurant.util;

public class Util {

    public static final int Lauch_Show_Time = 2000;
    public static final int Msg_Delay = 1000;// 1 sec
    public static final int Thread_Sleep = 1000;

    public static final String Intent_User_Name = "username";
    public static final String Intent_Password = "password";
    public static final String Intent_User_log_In = "user_login_true";
    public static final String Intent_Zip_Code = "zip_code";
    public static final String Intent_City_Name = "city_name";
    public static final int Search_Result_Done = 100;
    public static final String Intent_Restaurant_Node_List = "restaurant_node_list";
    public static final String Intent_Restaurant_Node = "restaurant_node";
    public static final String Intent_Location = "search_location";
    
    public static final String Pref_Name = "Chinese_Restaurant_Preference";
    public static final String First_Launch = "First_Launch";
    
    //google place search value
    public static final String Place_Text_Search_Json = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";  
    public static final String Place_Text_Search_Xml = "https://maps.googleapis.com/maps/api/place/textsearch/xml?query=";
    public static final String Place_Radar_Search_Json = "https://maps.googleapis.com/maps/api/place/radarsearch/json?";
    public static final String Place_Api_Key = "&key=AIzaSyCAgUdgdGM2_2k96IwK2uhGXcnpGD5Pu6E";
    public static final String Place_Text_Search_Query = "chinese restaurants+in+";
    public static final String Place_Radar_Search_Name = "&name=chinese+restaurant";
    public static final String Place_Sensor_True = "&sensor=true";
    public static final String Place_Sensor_False = "&sensor=false";
    public static final String Place_Page_Token = "&pagetoken=";
    
    //google place result value
    public static final String Place_Request_Result_Results = "results";
    public static final String Place_Request_Result_Type = "types";
    public static final String Place_Request_Result_Geometry = "geometry";
    public static final String Place_Request_Result_Location = "location";
    public static final String Place_Request_Result_Lat = "lat";
    public static final String Place_Request_Result_Lng = "lng";
    public static final String Place_Request_Result_Formatted_Address = "formatted_address";
    public static final String Place_Request_Result_Icon = "icon";
    public static final String Place_Request_Result_Id = "id";
    public static final String Place_Request_Result_Name = "name";
    public static final String Place_Request_Result_Opening_Hours = "opening_hours";
    public static final String Place_Request_Result_Open_Now = "open_now";
    public static final String Place_Request_Result_Price_Level = "price_level";
    public static final String Place_Request_Result_Rating = "rating";
    public static final String Place_Request_Result_Reference = "reference";
    public static final String Place_Request_Next_Page_Token = "next_page_token";
    
}
