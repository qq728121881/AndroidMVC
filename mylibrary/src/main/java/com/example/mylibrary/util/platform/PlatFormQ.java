package com.example.mylibrary.util.platform;

import android.app.Application;
import android.os.Environment;

import java.io.File;


public class PlatFormQ implements PlatForm {

    public static String USER_URI; //用户文件路径

    private String mFilePath;
    private Application mApp;

    public PlatFormQ(Application application, String filePath){
        mApp = application;
        mFilePath = filePath;
    }

    @Override
    public void adapter() {
        adapterFileStorage();
    }

    private void adapterFileStorage(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            File[] documents = mApp.getApplicationContext().getExternalFilesDirs("Documents");
            if(documents != null && documents.length > 0){
                USER_URI = documents[0].getAbsolutePath() + "/";
                return;
            }
        }

        USER_URI = Environment.getExternalStorageDirectory() + mFilePath;
    }
}
