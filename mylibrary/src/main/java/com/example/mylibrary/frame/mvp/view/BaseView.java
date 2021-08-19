package com.example.mylibrary.frame.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.frame.mvp.presenter.IPresenter;
import com.example.mylibrary.frame.util.viewcontroller.ViewControllerFactory;


public abstract class BaseView implements IView {

    private View mRootView;
    protected Activity mActivity;

    private IPresenter mPresenter;
    private final Object mLock = new Object();

    private ViewControllerFactory mViewControllerFactory;

    @Override
    public void createView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
        mRootView = inflater.inflate(getRootViewId(), parent, false);
    }

    @Override
    public void findViews() {
        mViewControllerFactory = new ViewControllerFactory(mRootView, null);//不设置点击事件
        initView();
    }

    @Override
    public void releaseViews() {
        mRootView = null;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void bindPresenter(IPresenter presenter) {
        synchronized (mLock){
            mPresenter = presenter;
        }
    }

    @Override
    public void onPresenterDestroy() {
        releaseViews();
    }

    protected abstract int getRootViewId();

    protected abstract void initView();

    public <T extends View> T get(int resId) {
        return (T)mViewControllerFactory.get(resId);
    }

    public <T> void bindData(T data) {

    }

    public void throwError(Throwable e) {

    }
}
