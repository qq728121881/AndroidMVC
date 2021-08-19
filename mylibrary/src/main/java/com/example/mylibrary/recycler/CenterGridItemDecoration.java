package com.example.mylibrary.recycler;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * gridlayoutManager，初始状态会把recycler等分成spancount个单元格，然后去布局
 * 如果需要调整距离，则改变outRect的间隔，这个outRect间隔是相对于单元格的开始位置的
 *
 * grid整体居中显示样式
 * */
public class CenterGridItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 横向个数
     * */
    int spanCount;
    /**
     * 间隔需要适配的宽度
     * */
    private int spacingAdapterWidth;
    /**
     * 纵向间隔
     * */
    int verticalSpacing;
    /**
     * item的宽度
     * */
    int itemWidth;

    public CenterGridItemDecoration(int spanCount) {
        this.spanCount = spanCount;
    }

    public CenterGridItemDecoration(int spanCount, int verticalSpacing) {
        this.spanCount = spanCount;
        this.verticalSpacing = verticalSpacing;
    }

    public CenterGridItemDecoration(int spanCount, int verticalSpacing, int itemWidth) {
        this.spanCount = spanCount;
        this.verticalSpacing = verticalSpacing;
        this.itemWidth = itemWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        //纵向间隔
        if (verticalSpacing > 0) {
            if (position >= spanCount) outRect.top = verticalSpacing;
        }
        int column = position % spanCount;
        //横向间隔
        if (spacingAdapterWidth == 0) {

            if (itemWidth == 0) {
                itemWidth = view.getMeasuredWidth();
            }

            if (itemWidth > 0) {
                int remainWidth = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight() - itemWidth * spanCount;
                int defaultSpacing = remainWidth / spanCount;
                spacingAdapterWidth = remainWidth / (spanCount - 1) - defaultSpacing;
            }
        }
        outRect.left = spacingAdapterWidth * column;
    }
}
