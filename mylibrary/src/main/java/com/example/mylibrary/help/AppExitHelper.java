package com.example.mylibrary.help;

import android.app.Activity;
import android.app.ActivityManager;


import com.example.mylibrary.widget.UICToast;

import static android.content.Context.ACTIVITY_SERVICE;

public class AppExitHelper {

    Activity activity;
    int limitTime;

    private long exitTime;

    public AppExitHelper(Activity activity) {
        this.activity = activity;
        limitTime = 2000;
        exitTime = 0;
    }

    public AppExitHelper(Activity activity, int limitTime) {
        this.activity = activity;
        this.limitTime = limitTime;
        exitTime = 0;
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > limitTime) {
            UICToast.instance().showNormalToast("再按一次退出应用");
            exitTime = System.currentTimeMillis();
        } else {
            activity.finish();
            ActivityManager manager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(activity.getPackageName());
        }
    }
}
