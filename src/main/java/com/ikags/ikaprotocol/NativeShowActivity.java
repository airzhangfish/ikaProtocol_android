package com.ikags.ikaprotocol;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.ikags.framwork.web.WebConfig;


public class NativeShowActivity extends Activity {
    private static final String TAG = "NativeShowActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        initPageData();
        initViews();
        initPageDataOver();
    }

    public void initPageData() {

    }


    public void initPageDataOver() {

    }


    private void initViews() {

    }



}
