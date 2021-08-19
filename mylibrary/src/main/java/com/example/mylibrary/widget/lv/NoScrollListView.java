package com.example.mylibrary.widget.lv;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zt on 2017/12/13.
 * 不可滑动的 ListView
 */

public class NoScrollListView extends ListView {

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
