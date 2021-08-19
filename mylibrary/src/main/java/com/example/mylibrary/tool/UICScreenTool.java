package com.example.mylibrary.tool;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class UICScreenTool {

    public static final String TAG = "UICScreenTool";

    private static DisplayMetrics sDisplayMetrics;
    private static Resources sResources;

    //使用前，需要初始化；最好是在应用Application中做初始化
    public static void instance(Context context){
        if(sDisplayMetrics == null){
            sResources = context.getApplicationContext().getResources();
            sDisplayMetrics = sResources.getDisplayMetrics();
        }
    }

    public static int getScreenWidth() {
        return sDisplayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        return sDisplayMetrics.heightPixels;
    }

    public static float getScreenDensity() {
        return sDisplayMetrics.density;
    }

    public static int getScreenDensityDpi() {
        return sDisplayMetrics.densityDpi;
    }

    public static int getStatusBarHeight() {
        int height = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        Log.e(TAG, "[StatusBarHeight] " + height);
        return height;
    }

    public static int getNavigationBarHeight() {
        int height = 0;
        int resourceId = Resources.getSystem().getIdentifier("navigation_bar_height","dimen", "android");
        if(resourceId > 0){
            height = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        Log.e(TAG, "[NavigationBarHeight] " + height);
        return height;
    }
}
