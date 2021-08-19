package com.example.myapplication.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.example.myapplication.model.HomeModel;
import com.example.myapplication.view.HomeView;
import com.example.mylibrary.frame.mvp.presenter.ActivityPresenter;


public class HomeActivity extends ActivityPresenter<HomeModel, HomeView> {


    @Override
    public Class<HomeModel> getRootModelClass() {
        return HomeModel.class;
    }

    @Override
    public Class<HomeView> getRootViewClass() {
        return HomeView.class;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @Override
    public void inCreate(Bundle savedInstanceState) {

        getModelRef().login();
        getModelRef().test();


    }

    @Override
    public <T> void onSuccess(T result) {
        super.onSuccess(result);
    }
}