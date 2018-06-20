package com.ikags.framwork.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


import com.ikags.framwork.IKALog;
import com.ikags.framwork.MainConfig;

import java.net.URLDecoder;
import java.sql.Array;
import java.util.ArrayList;

public class WebConfig {
    public static final String TAG = "WebConfig";
    public static ArrayList<WebProtocol> mProtocollist = null;

    /**
     * 对webview进行配置的方法。
     *
     * @param acti
     * @param webView1
     * @param arraylist
     * @param isopenout
     */
    public static void setIKAWebSetting(final Activity acti, WebView webView1, ArrayList<WebProtocol> arraylist, boolean isopenout) {
        setIKAWebSetting(acti, webView1, arraylist, isopenout, false);
    }


    private static void setIKAWebSetting(final Activity acti, WebView webView1, ArrayList<WebProtocol> arraylist, boolean isopenout, boolean isLongclick) {
        try {
            IKAWebChromeClient wcc = new IKAWebChromeClient(acti);
            IKAWebViewClient wvc = new IKAWebViewClient(acti, isopenout);

            mProtocollist = arraylist;
            webView1.setWebViewClient(wvc);
            webView1.setWebChromeClient(wcc);
            WebSettings settings = webView1.getSettings();
            settings.setBlockNetworkImage(true);
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setAppCacheEnabled(true);
            JSKit js = new JSKit(acti);
            webView1.addJavascriptInterface(js, "ikapp");
            settings.setAllowFileAccess(true);
            if (Build.VERSION.SDK_INT >= 16) {
                settings.setAllowFileAccessFromFileURLs(true);
                settings.setAllowUniversalAccessFromFileURLs(true);
            }

            View.OnLongClickListener olcl = new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    try {
                        WebView.HitTestResult result = ((WebView) view).getHitTestResult();
                        if (result != null) {
                            int type = result.getType();
                            if (type == WebView.HitTestResult.IMAGE_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                                //打开图片
//                                String str = result.getExtra();
//                                Intent intent = new Intent();
//                                intent.setClass(acti, SinglePicActivity.class);
//                                intent.putExtra("id", "0");
//                                intent.putExtra("url", str);
//                                acti.startActivity(intent);
                                Toast.makeText(acti, "pic url is " + result.getExtra(), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return true;
                }

            };


            if (isLongclick == true) {
                webView1.setOnLongClickListener(olcl);
            } else {

                webView1.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void showWebPage(Context mContext, String title, String url, int resultcode) {

        Bundle mbundle = new Bundle();
        mbundle.putString("url", "" + url);
        mbundle.putString("title", "" + title);
        showWebPage(mContext, mbundle, resultcode);

    }

    private static void showWebPage(Context mContext, Bundle bundle, int resultcode) {
        try {

            Intent intent = new Intent();
            Class webactivity = Class.forName(MainConfig.defaultWebActivity);
            intent.setClass(mContext, webactivity);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            ((Activity) mContext).startActivityForResult(intent, resultcode);
        } catch (Exception ex) {
            if (mContext != null) {
                Toast.makeText(mContext, "showwebpage error", Toast.LENGTH_SHORT).show();
            }
            ex.printStackTrace();
        }
    };



    public static boolean setConfig(Context mContext, String url) {
        IKALog.v(TAG, "setConfig");
        return true;
    }


    public static boolean gotoPage(Context mContext, String url) {

        if (mProtocollist != null && !mProtocollist.isEmpty()) {
            for (int i = 0; i < mProtocollist.size(); i++) {
                WebProtocol webpro = mProtocollist.get(i);
                if (webpro.isMatch(mContext, url)) {
                    boolean donework=webpro.onWork(mContext, url);
                    return donework;
                }
            }
        }
        return false;
    }



}
