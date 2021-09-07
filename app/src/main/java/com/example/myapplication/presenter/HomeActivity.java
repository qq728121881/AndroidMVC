package com.example.myapplication.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.R;
import com.example.myapplication.model.HomeModel;
import com.example.myapplication.view.HomeView;
import com.example.mylibrary.arouter.ARouterPath;
import com.example.mylibrary.arouter.ARouterWrapper;
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

        get(R.id.button).setOnClickListener(this);




    }

    @Override
    public <T> void onSuccess(T result) {
        super.onSuccess(result);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.button:

                ARouter.getInstance().build(ARouterPath.APP_SETING_MAIN).navigation();

                break;


        }


    }
}