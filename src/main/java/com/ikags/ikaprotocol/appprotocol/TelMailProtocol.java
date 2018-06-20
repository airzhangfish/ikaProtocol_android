package com.ikags.ikaprotocol.appprotocol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ikags.framwork.web.WebProtocol;

/**
 * Created by airzhangfish on 2018/6/20.
 */

public class TelMailProtocol implements WebProtocol {

    @Override
    public boolean isMatch(Context mContext, String url) {
        if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onWork(Context mContext, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
