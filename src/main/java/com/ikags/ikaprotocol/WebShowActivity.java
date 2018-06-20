package com.ikags.ikaprotocol;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import android.widget.Toast;

import com.ikags.framwork.MainConfig;
import com.ikags.framwork.web.WebConfig;


public class WebShowActivity extends Activity {
    private static final String TAG = "WebShowActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webshow);
        initPageData();
        initViews();
        initPageDataOver();
    }

    String mUrl=null;
    String title=null;
    boolean showbutton=false;
    public void initPageData() {
         mUrl = this.getIntent().getStringExtra("url");
        title = this.getIntent().getStringExtra("title");
        showbutton = this.getIntent().getBooleanExtra("showreflash",false);
    }


    public void initPageDataOver() {

    }

    WebView webView1 = null;

    private void initViews() {


//        if(!TextUtils.isEmpty(title)){
//            TitlebarManagerV2.initTitlebar(this, ""+title, false);
//        }else{
//            TitlebarManagerV2.initTitlebar(this, getString(R.string.app_name), false);
//        }
//
//        TitlebarManagerV2.initTitlebarBgColor(this,Def.mainRedColor);
//
//        if(showbutton==true){
//            TitlebarManagerV2.showToolButton(WebShowActivity.this,1,R.drawable.v2_tools_reflash,new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    //zhangdan
//                    webView1.loadUrl("" + mUrl+"&reflash="+ System.currentTimeMillis());
//                }
//
//            });
//
//        }



        webView1 = (WebView) this.findViewById(R.id.webView1);
        WebConfig.setIKAWebSetting(this, webView1, MainConfig.getWebProtocol(),true);
        webView1.loadUrl("" + mUrl);

    }



    public void loadpage(String url){
        if(webView1!=null){
            webView1.loadUrl("" + url+"&reflash="+ System.currentTimeMillis());
        }
    }


}
