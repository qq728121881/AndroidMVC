package com.example.mylibrary.tool;

import android.content.Context;
import android.util.DisplayMetrics;

public class UICDisplayTool {

    private static DisplayMetrics mDisplayMetrics;

    //使用前，需要初始化；最好是在应用Application中做初始化
    public static void instance(Context context){
        if(mDisplayMetrics == null){
            mDisplayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        }
    }

    public static int dp2Px(float dpValue){
        return (int) (mDisplayMetrics.density*dpValue + 0.5f);
    }

    public static int px2Dp(float pxValue){
        return (int) (pxValue/mDisplayMetrics.density + 0.5f);
    }

    public static int sp2Px(float spValue){
        return (int) (spValue*mDisplayMetrics.scaledDensity + 0.5f);
    }

    public static int px2Sp(float pxValue){
        return (int) (pxValue/mDisplayMetrics.scaledDensity + 0.5f);
    }

}
