package com.ikags.framwork.web;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * android webview 通过JS来调用android原生方法
 * Created by airzhangfish on 2018/3/6.
 */


public class JSKit {

    Activity mActivity;

    public JSKit(Activity acti) {
        mActivity = acti;
    }



    @JavascriptInterface
    public void gotopage(String url) {
        WebConfig.gotoPage(mActivity, url);
    }

    @JavascriptInterface
    public void setconfig(String url) {
        WebConfig.setConfig(mActivity, url);
    }


}
