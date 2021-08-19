package com.example.mylibrary.frame.util.viewcontroller;

import android.view.View;

public interface IViewControllerCommon {

    <T extends View> T get(int resId);
}
