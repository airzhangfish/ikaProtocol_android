package com.ikags.ikaprotocol;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ikags.framwork.IKALog;
import com.ikags.framwork.web.WebConfig;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends Activity {


    ImageView imageView = null;
    Button button=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgtest();
        netpost2("http://www.ikags.com", "");
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gototest();
            }
        });
    }


    public void imgtest() {
        imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load("http://www.ikags.com/images/menu_contact.jpg")
                .into(imageView);
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    public void netpost2(String url, String json) {

        try {


            Request request = new Request.Builder()
                    .url(url)
                    .build();


//            RequestBody body = RequestBody.create(JSON, json);
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("MainActivity","error="+e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String data = response.body().string();
                    Log.v("MainActivity","data2="+data);
                }
            });





        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    public void gototest(){
        IKALog.v("MainActivity","gototest");


        //"file:///android_asset/index.html" ,"https://www.baidu.com"
        WebConfig.showWebPage(this,"web page","file:///android_asset/index.html",100);

    }


}
