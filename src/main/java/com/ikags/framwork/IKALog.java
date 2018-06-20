package com.ikags.framwork;


import android.view.View;
import android.view.ViewGroup;

/**
 * 日志打印类
 */

public class IKALog {

    public final static String TAG = "IKALog";

    public static boolean isPrintLog = true;

    public static void print(String msg) {
        if (isPrintLog) {
            System.out.print(msg == null ? "" : msg);
        }
    }

    public static void println(String msg) {

        if (isPrintLog) {
            System.out.println(msg == null ? "" : msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isPrintLog) {
            android.util.Log.i(tag, msg == null ? "" : msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isPrintLog) {
            android.util.Log.i(tag, msg == null ? "" : msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (isPrintLog) {
            android.util.Log.d(tag, msg == null ? "" : msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isPrintLog) {
            android.util.Log.d(tag, msg == null ? "" : msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (isPrintLog) {
            android.util.Log.e(tag, msg == null ? "" : msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isPrintLog) {
            android.util.Log.e(tag, msg == null ? "" : msg, tr);
        }
    }

    public static void v(String tag, String msg) {
        if (isPrintLog) {
            android.util.Log.v(tag, msg == null ? "" : msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isPrintLog) {
            android.util.Log.v(tag, msg == null ? "" : msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (isPrintLog) {
            android.util.Log.w(tag, msg == null ? "" : msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (isPrintLog) {
            android.util.Log.w(tag, msg == null ? "" : msg, tr);
        }
    }


    /**
     * 查看长日志
     * @param TAG
     * @param str
     */
    public static void vLongLog(String TAG, String str) {
        if (isPrintLog) {
            if (str == null) {
                return;
            }
            try {
                int presize = 3 * 1024;
                for (int i = 0; i < (str.length() / presize) + 1; i++) {
                    int startpos = presize * i;
                    int endpos = Math.min(startpos + presize, str.length());
                    v(TAG, str.substring(startpos, endpos));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 查看view树结构
     * @param TAG
     * @param view
     * @param space
     */
    public static void logViewTree(String TAG, View view, int space) {
        String data = "";
        for (int j = 0; j < space; j++) {
            data = data + "--";
        }
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            v(TAG, data + vg.getClass().getSimpleName());
            for (int i = 0; i < vg.getChildCount(); i++) {
                logViewTree(TAG, vg.getChildAt(i), space + 1);
            }
        } else if (view != null) {
            v(TAG, data + view.getClass().getSimpleName());
        } else {
            v(TAG, "view is null");
        }

    }


}
