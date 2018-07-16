package com.example.zn.weaherapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOError;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weatherapi extends AppCompatActivity {

    TextView Areaname;
    TextView Areatemp;
    TextView Areamain;
    TextView Areatemp2;

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    final ExecutorService service = Executors.newSingleThreadExecutor();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherapi);

        Areaname = findViewById(R.id.Area_name);
        Areatemp=findViewById(R.id.Area_temp);
        Areamain=findViewById(R.id.Area_main);
        Areatemp2=findViewById(R.id.temp);

        service.submit(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
                        .build();
                try {
                    final Response response = client.newCall(request).execute();
                    final String resStr = response.body().string();

                    System.out.println("str " + resStr);
                    WeatherConverter convertFromJson=new WeatherConverter();
                   final Weather weather= convertFromJson.convertFromJson(resStr);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("name " + weather.name);
                            Areaname.setText(weather.name);
                            Areatemp.setText(weather.temp_min+"°F"+" / "+weather.temp_max+"°F");
                            Areamain.setText(weather.main);
                            Areatemp2.setText(weather.temp);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


//        //建立Request，建立連線資訊
//        final Request request=new Request.Builder()
//                .url("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
//                .build();
//
//        //建立Call
//        Call call=client.newCall(request);
//
//        call.enqueue(new Callback(){
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result =response.body().string();
//                //textView.setText(result);
//                Log.d("Ok result",result);
//            }
//            @Override
//            public void onFailure(Call call,IOException e){
//                //連線失敗
//            }
//
//
//        });

    }
}
