package com.example.common.utils;

import android.util.Log;

public class LogUtils {

    private static  String TAG = "lizhe";
    private static Boolean isDebug = true;

    public static void d(String log) {
        if (isDebug) {
            Log.d(TAG, log);
        }
    }

}
