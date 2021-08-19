package com.example.mylibrary.tool.view;

import android.text.Layout;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * 自定义了TextView的省略样式
 * */
public class CustomEllipsizeEndStyle {

    /**
     * 是否第一次使用
     * */
    private boolean isFirstLanding;

    private CustomEllipsizeEndStyle(){
        isFirstLanding = true;
    }

    private void set(TextView tv, int maxLine){
        if(tv == null){
            return;
        }
        ViewTreeObserver observer = tv.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(!isFirstLanding){
                    return;
                }
                Layout layout2 = tv.getLayout();
                if(layout2 != null){
                    int lines = layout2.getLineCount();

                    /**
                     * 内容没有超过限制的行数，或者最后一行数据没满
                     * */
                    if(lines < maxLine || layout2.getEllipsisCount(lines-1) == 0) {
                        return;
                    }

                    String showText = tv.getText().toString();
                    showText = showText.substring(0, layout2.getLineVisibleEnd(lines-1) - layout2.getEllipsisCount(lines-1)).trim().concat("...");
                    tv.setText(showText);
                }
                isFirstLanding = false;
            }
        });
    }

    /**
     * 使用该方法统一修改了TextView超出行数限制的省略样式
     *
     * @param maxLine TextView显示的最大行数
     * @param tv 需要处理的TextView
     * */
    public static void apply(TextView tv, int maxLine){
        CustomEllipsizeEndStyle style = new CustomEllipsizeEndStyle();
        style.set(tv, maxLine);
    }
}
