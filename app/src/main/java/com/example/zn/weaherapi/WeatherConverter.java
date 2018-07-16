package com.example.zn.weaherapi;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherConverter {

    public Weather convertFromJson(String json) {

        String main=null;
        String temp=null;
        String temp_min = null;
        String temp_max = null;
        String speed = null;
        String name = null;

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject weatherjsonObject=(JSONObject) jsonObject.getJSONArray("weather").get(0);

            main=weatherjsonObject.getString("main");//天氣

            JSONObject mainObject = (JSONObject) jsonObject.getJSONObject("main");
            temp=mainObject.getString("temp");//溫度
            temp_min=mainObject.getString("temp_min");
            temp_max=mainObject.getString("temp_max");

            JSONObject windjsonObject=(JSONObject) jsonObject.getJSONObject("wind");
            speed=windjsonObject.getString("speed");//風速

            //JSONObject namejsonObject=(JSONObject) jsonObject.getJSONObject("name");
            name=jsonObject.getString("name");


        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return new Weather(main,temp,temp_min,temp_max,name,speed);
    }


}
