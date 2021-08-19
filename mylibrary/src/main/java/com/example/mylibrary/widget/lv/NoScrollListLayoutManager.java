package com.example.mylibrary.widget.lv;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoScrollListLayoutManager extends LinearLayoutManager {

    public NoScrollListLayoutManager(Context context) {
        super(context);
    }

    public NoScrollListLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoScrollListLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        if(getOrientation() == RecyclerView.VERTICAL){
            return false;
        }
        return super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        if(getOrientation() == RecyclerView.HORIZONTAL){
            return false;
        }
        return super.canScrollHorizontally();
    }
}
