package com.example.mylibrary.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @作者 邸昌顺
 * @时间 2019/3/12 18:06
 * @描述
 */
public class NetworkTool {

    private static Context appContext;

    public static void initialize(Context appContext){
        NetworkTool.appContext = appContext;
    }

    //是否连接了网络
    public static boolean isNetworkAvaliable(){
        ConnectivityManager conManager = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

}
