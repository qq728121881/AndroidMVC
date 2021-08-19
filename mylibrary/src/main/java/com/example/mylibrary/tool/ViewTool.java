package com.example.mylibrary.tool;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class ViewTool {

    /**
     * 设置View的margin
     * */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
//            v.requestLayout();
        }
    }

    /**
     * 设置popupwindow弹出时背景变化
     *
     * @param activity
     * @param bgAlpha 透明度 1.0f 取消显示；0 完全遮住，不透明，黑屏
     * */
    public static void setBackground(Activity activity, float bgAlpha){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
