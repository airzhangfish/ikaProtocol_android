package com.ikags.framwork.web;



import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ikags.framwork.IKALog;
import com.ikags.ikaprotocol.R;

import java.io.InputStream;

public class IKAWebViewClient extends WebViewClient {
    public static final String TAG = "IKAWebViewClient";
    Activity mContext = null;
    boolean isOutopen = false;
    public static long endtime = 0;
    public static long starttime = 0;

    public IKAWebViewClient(Activity con, boolean openout) {
        mContext = con;
        isOutopen = openout;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        IKALog.v(TAG, "url=" + url);
        if (url == null) {
            return true;
        }


        boolean isused = WebConfig.gotoPage(mContext, url);

        if (isused == false) {
            boolean isCustom = onCustomLoading(view, url);
            if (isCustom) {
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
        return true;

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        starttime = System.currentTimeMillis();
        view.getSettings().setBlockNetworkImage(true);

        ProgressBar webview_progressBar = (ProgressBar) mContext.findViewById(R.id.webview_progressBar);
        if (webview_progressBar != null) {
            webview_progressBar.setVisibility(View.VISIBLE);
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.getSettings().setBlockNetworkImage(false);
        endtime = System.currentTimeMillis();
        //Toast.makeText(mContext, "spendtime="+(endtime-starttime), Toast.LENGTH_SHORT).show();
        ProgressBar webview_progressBar = (ProgressBar) mContext.findViewById(R.id.webview_progressBar);
        if (webview_progressBar != null) {
            webview_progressBar.setVisibility(View.GONE);
        }
        super.onPageFinished(view, url);
    }


    public boolean onCustomLoading(WebView view, String url) {
        return false;
    }


    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view,  String url) {
        WebResourceResponse response=null;
        if(url!=null){
            IKALog.v(TAG,"shouldInterceptRequest_url="+url);
            if (url.startsWith("localfile://")&&(url.endsWith(".png")||url.endsWith(".jpg"))) {
                try {
                    String newurl=url.substring("http://android_asset/".length(),url.length());
                    IKALog.v(TAG,"localfileurl="+newurl);
                    InputStream localCopy = mContext.getAssets().open(newurl);

                    if(url.endsWith(".png")==true){
                        response = new WebResourceResponse("image/png", "UTF-8", localCopy);
                    }else{
                        response = new WebResourceResponse("image/jepg", "UTF-8", localCopy);
                    }
                    return response;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.shouldInterceptRequest(view, url);
    }





}
