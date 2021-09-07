package com.example.seting.library.presonter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mylibrary.arouter.ARouterPath;
import com.example.mylibrary.frame.mvp.presenter.ActivityPresenter;
import com.example.seting.R;
import com.example.seting.library.model.SetingModel;
import com.example.seting.library.view.SetingView;

@Route(path = ARouterPath.APP_SETING_MAIN)
public class MainActivity extends ActivityPresenter<SetingModel, SetingView> {


    @Override
    public Class<SetingModel> getRootModelClass() {
        return SetingModel.class;
    }

    @Override
    public Class<SetingView> getRootViewClass() {
        return SetingView.class;
    }

    @Override
    public Activity bindActivity() {
        return null;
    }

    @Override
    public void inCreate(Bundle savedInstanceState) {

    }

    @Override
    public <T> void onSuccess(T result) {
        super.onSuccess(result);
    }
}