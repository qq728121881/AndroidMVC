package com.example.mylibrary.frame.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.mylibrary.config.CommonConfig;
import com.example.mylibrary.frame.util.viewcontroller.IViewControllerClick;
import com.example.mylibrary.frame.util.viewcontroller.IViewControllerCommon;
import com.example.mylibrary.frame.util.viewcontroller.IViewControllerLifeCycleClick;
import com.example.mylibrary.frame.util.viewcontroller.ViewControllerFactory;
import com.example.mylibrary.manager.ActManager;


public class BaseActivity extends FragmentActivity implements IViewControllerClick, IViewControllerCommon, View.OnClickListener, IViewControllerLifeCycleClick {

    private ViewControllerFactory mViewControllerFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mViewControllerFactory = new ViewControllerFactory(view, this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mViewControllerFactory = new ViewControllerFactory(getLayoutInflater().inflate(layoutResID, null), this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mViewControllerFactory = new ViewControllerFactory(view, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActManager.getInstance().removeActivity(this);
    }

    //保持app内字体大小不变
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(CommonConfig.APP_FONTSIZE_CONTROLLER){
            if (newConfig.fontScale != 1){
                getResources();
            }
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        if(CommonConfig.APP_FONTSIZE_CONTROLLER){
            Resources res = super.getResources();
            if (res.getConfiguration().fontScale != 1) {
                Configuration newConfig = new Configuration();
                newConfig.setToDefaults();
                res.updateConfiguration(newConfig, res.getDisplayMetrics());
            }
            return res;
        }else {
            return super.getResources();
        }
    }

    @Override
    public void bindSingleClicks(int[] resIds) {
        mViewControllerFactory.bindSingleClicks(resIds);
    }

    @Override
    public void bindSingleClicks(int[] resIds, int seconds) {
        mViewControllerFactory.bindSingleClicks(resIds, seconds);
    }

    @Override
    public void resetSingleClicks() {
        mViewControllerFactory.resetSingleClicks();
    }

    @Override
    public void resetSingleClicks(int[] resIds) {
        mViewControllerFactory.resetSingleClicks(resIds);
    }

    @Override
    public void bindNormalClicks(int[] resIds) {
        mViewControllerFactory.bindNormalClicks(resIds);
    }

    @Override
    public <T extends View> T get(int resId) {
        return (T) mViewControllerFactory.get(resId);
    }

    @Override
    public void onResetClickViews() {
        mViewControllerFactory.onResetClickViews();
    }

    @Override
    public void addClickViews(View[] views) {
        mViewControllerFactory.addClickViews(views);
    }

    @Override
    public void onClick(View v) {

    }
}
