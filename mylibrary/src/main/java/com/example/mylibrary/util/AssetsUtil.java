package com.example.mylibrary.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetsUtil {

    private volatile static AssetsUtil sAssetsUtil = null;

    private AssetsUtil(){}

    public static AssetsUtil getInstance(){
        if(sAssetsUtil == null){
            synchronized (AssetsUtil.class){
                if(sAssetsUtil == null){
                    sAssetsUtil = new AssetsUtil();
                }
            }
        }
        return sAssetsUtil;
    }

    public String readStringFromAssets(Context context, String assetsName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(assetsName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
