package com.ikags.framwork.web;

import android.content.Context;

/**
 * Created by airzhangfish on 2018/6/20.
 */

public interface WebProtocol {
    boolean isMatch(Context mContext, String url);
    boolean onWork(Context mContext, String url);
}
