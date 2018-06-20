package com.ikags.framwork.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.ikags.framwork.IKALog;



public class IKAWebChromeClient extends WebChromeClient{

	Activity mContext=null;
	public IKAWebChromeClient(Activity con){
		mContext=con;
	}
	
	   @Override
	   public void onShowCustomView(View view, CustomViewCallback callback) {
	    IKALog.v("IKAWebChromeClient", "onShowCustomView");
	   }    
	   

	
	@Override
	public void onReceivedTitle(WebView view, String title) {
		super.onReceivedTitle(view, title);
	}
	
    @Override  
    public boolean onJsAlert(WebView view, String url, String message,  
            final JsResult result) {  
              AlertDialog.Builder b2 = new AlertDialog.Builder(mContext)  
              .setTitle("").setMessage(message)
              .setPositiveButton("ok",  
                      new AlertDialog.OnClickListener() {  
                          @Override  
                          public void onClick(DialogInterface dialog,  
                                  int which) {  
                              result.confirm();  
                          }  
                      });  
              b2.setCancelable(false);  
              b2.create();  
              b2.show();    
          return true;  
    }  
	
}
