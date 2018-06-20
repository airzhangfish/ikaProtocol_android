package com.ikags.ikaprotocol.appprotocol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ikags.framwork.web.WebConfig;
import com.ikags.framwork.web.WebProtocol;

/**
 * Created by airzhangfish on 2018/6/20.
 */

public class AppWebProtocol implements WebProtocol {

    @Override
    public boolean isMatch(Context mContext, String url) {
        if (url.startsWith("ikapp://appweb=")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onWork(Context mContext, String url) {
        String str = url.substring("ikapp://web=".length(), url.length());
        String neturl = "https://www.baidu.com/s?wd=" + str;
        WebConfig.showWebPage(mContext, "页面测试", neturl,100);
        return true;
    }
}
