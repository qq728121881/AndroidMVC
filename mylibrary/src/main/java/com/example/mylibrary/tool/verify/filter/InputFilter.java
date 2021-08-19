package com.example.mylibrary.tool.verify.filter;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.mylibrary.widget.UICToast;


public abstract class InputFilter extends Filter {

    protected boolean isMatch;

    public InputFilter(){
        isMatch = false;
    }

    public abstract Filter emptyFilter(String content);

    public abstract Filter formatFilter(String content);

    public Filter emptyFilter(String content, @NonNull String noticeMsg) {
        if(isMatch){
            return this;
        }
        isMatch = TextUtils.isEmpty(content);
        if(isMatch){
            UICToast.instance().showNormalToast(noticeMsg);
        }
        return this;
    }

    public Filter formatFilter(String content, @NonNull String noticeMsg) {
        if(isMatch){
            return this;
        }
        String passwordReg = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{8,50})$";
        isMatch = !content.matches(passwordReg);
        if(isMatch){
            UICToast.instance().showNormalToast(noticeMsg);
        }
        return this;
    }
}
