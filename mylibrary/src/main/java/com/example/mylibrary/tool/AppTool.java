package com.example.mylibrary.tool;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;


import com.example.mylibrary.tool.log.DLog;
import com.example.mylibrary.util.FormatUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AppTool {

    private static long DEFAULT_APP_VERSIONCODE = 0L;
    private long versionCode;

    private static volatile AppTool sAppTool = null;

    private AppTool(){
        versionCode = DEFAULT_APP_VERSIONCODE;
    }

    public static AppTool getInstance(){
        if(sAppTool == null){
            synchronized(AppTool.class){
                if(sAppTool == null){
                    sAppTool = new AppTool();
                }
            }
        }
        return sAppTool;
    }

    private Application mApp;

    public void setAppTool(Application application){
        this.mApp = application;
        NetworkTool.initialize(application);
        UICScreenTool.instance(application);
        UICSpTool.getInstance().init(application);
        UICDisplayTool.instance(application);
    }

    public Application getApplication(){
        if(mApp == null){
            throw new NullPointerException(FormatUtil.formatLogString("you need init the Application in your Base Application"));
        }
        return mApp;
    }

    /**
     * 取APP版本号
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public long getAppVersionCode() {
        if (versionCode == DEFAULT_APP_VERSIONCODE) {
            try {
                PackageManager mPackageManager = mApp.getPackageManager();
                PackageInfo _info = mPackageManager.getPackageInfo(mApp.getPackageName(), 0);
                versionCode = 1;
            } catch (PackageManager.NameNotFoundException e) {
                versionCode = DEFAULT_APP_VERSIONCODE;
            }
        }
        DLog.d("versionCode=" + versionCode);
        return versionCode;
    }

    public String getAppVersionName() {
        String versionName ;
        try {
            PackageManager mPackageManager = mApp.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApp.getPackageName(), 0);
            versionName = _info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "--";
        }
        DLog.d("versionName=" + versionName);
        return versionName;
    }

    public String getDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sDateFormat.format(new Date());
    }
}
