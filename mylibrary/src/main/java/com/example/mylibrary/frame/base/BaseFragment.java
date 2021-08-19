package com.example.mylibrary.frame.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylibrary.frame.util.viewcontroller.IViewControllerClick;
import com.example.mylibrary.frame.util.viewcontroller.IViewControllerCommon;
import com.example.mylibrary.frame.util.viewcontroller.ViewControllerFactory;


public class BaseFragment extends Fragment implements IViewControllerClick, IViewControllerCommon, View.OnClickListener {

    private ViewControllerFactory mViewControllerFactory;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewControllerFactory = new ViewControllerFactory(view, this);
    }

    @Override
    public void onClick(View v) {

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
        return (T)mViewControllerFactory.get(resId);
    }
}
