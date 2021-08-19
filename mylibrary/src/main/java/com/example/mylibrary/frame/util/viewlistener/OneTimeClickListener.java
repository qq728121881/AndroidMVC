package com.example.mylibrary.frame.util.viewlistener;

import android.view.View;

import java.util.Calendar;

/**
 * View点击事件响应接口
 *
 * 在设定时间内View的点击事件只响应一次
 * */
public class OneTimeClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private OnOneTimeClickListener mOnOneTimeClickListener;

    public OneTimeClickListener(OnOneTimeClickListener checkDoubleClick){
        this.mOnOneTimeClickListener = checkDoubleClick;
    }

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            mOnOneTimeClickListener.onOneTimeClick(v);
        }
    }

    public interface OnOneTimeClickListener{
        void onOneTimeClick(View view);
    }

}
