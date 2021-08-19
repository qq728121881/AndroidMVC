package com.example.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mylibrary.tool.AppTool;
import com.example.mylibrary.util.Utils;

public class BassApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppTool.getInstance().setAppTool(this);
        ARouter.init(this);
        Utils.init(this);
//        if (Utils.isAppDebug()) {
//            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
//            ARouter.openDebug();
//            ARouter.openLog();
//        }

    }
}
