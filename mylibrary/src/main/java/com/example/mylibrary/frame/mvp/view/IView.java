package com.example.mylibrary.frame.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.frame.mvp.presenter.IPresenterLifeCycle;


public interface IView extends IPresenterLifeCycle {

    void createView(LayoutInflater inflater, ViewGroup parent, Bundle bundle);

    void findViews();

    void releaseViews();

    public View getRootView();

    void setActivityContext(Activity activity);
}
