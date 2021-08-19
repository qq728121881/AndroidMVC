package com.example.mylibrary.widget.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class InterceptScrollView extends ScrollView {

    public InterceptScrollView(Context context) {
        this(context,null);
    }

    public InterceptScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public InterceptScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //关键点在这
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}