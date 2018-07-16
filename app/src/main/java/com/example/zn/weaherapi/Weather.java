package com.example.zn.weaherapi;


import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

    String main;
    String temp;
    String temp_min;
    String temp_max;
    String name;
    String wind;


//    Weather(String json) throws JSONException {
//
//    }


    public Weather(String main,String temp,String temp_min, String temp_max, String name, String wind) {
        this.main=main;
        this.temp=temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.name = name;
        this.wind = wind;
    }

}
