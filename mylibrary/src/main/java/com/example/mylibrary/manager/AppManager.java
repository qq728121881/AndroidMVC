package com.example.mylibrary.manager;

import android.app.ActivityManager;
import android.content.Context;

public class AppManager {

    private static volatile AppManager sAppManager = null;

    private AppManager(){}

    public static AppManager getInstance(){
        if(sAppManager == null){
            synchronized (AppManager.class){
                if(sAppManager == null){
                    sAppManager = new AppManager();
                }
            }
        }
        return sAppManager;
    }

    //退出APP
    public void exitApp(Context context){
        try {
            ActManager.getInstance().finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {}
    }
}
