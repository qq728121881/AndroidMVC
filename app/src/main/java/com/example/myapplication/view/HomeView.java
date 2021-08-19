package com.example.myapplication.view;


import android.widget.TextView;


import com.example.myapplication.R;
import com.example.mylibrary.frame.mvp.view.BaseView;


public class HomeView extends BaseView {


    @Override
    protected int getRootViewId() {
        return R.layout.activity_test2;
    }


    @Override
    protected void initView() {

        TextView text = get(R.id.text);
        text.setText("1");


    }
}
