package com.example.mylibrary.tool.view;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * EditText失去监听之后，隐藏输入法
 * 使用方法：
 *      et.setOnFocusChangeListener(new HideSoftKeyListener());
 *      需要在适当时候控制清除et控件的焦点
 *
 * @author dichangshun
 * */
public class HideSoftKeyListener implements View.OnFocusChangeListener {

    private boolean mHasFocus;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v instanceof EditText){
            if(mHasFocus && !hasFocus){
                EditText etV = (EditText) v;
                InputMethodManager inputMethodManager = ((InputMethodManager) etV.getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                if(inputMethodManager != null){
                    inputMethodManager.hideSoftInputFromWindow(etV.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return;
            }
        }
        this.mHasFocus = hasFocus;
    }
}
