package com.ikags.ikaprotocol.appprotocol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ikags.framwork.web.WebProtocol;
import com.ikags.ikaprotocol.NativeShowActivity;

/**
 * Created by airzhangfish on 2018/6/20.
 */

public class NativetestProtocol implements WebProtocol {

    @Override
    public boolean isMatch(Context mContext, String url) {
        if (url.startsWith("ikapp://nativetest=")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onWork(Context mContext, String url) {
        try {
            String str = url.substring("ikapp://nativetest=".length(), url.length());
            Intent intent = new Intent();
            intent.setClass(mContext, NativeShowActivity.class);
            intent.putExtra("id", ""+str);
            intent.putExtra("type", 1);
            mContext.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
