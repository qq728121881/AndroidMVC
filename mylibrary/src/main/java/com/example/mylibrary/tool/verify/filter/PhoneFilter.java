package com.example.mylibrary.tool.verify.filter;

import android.text.TextUtils;

import com.example.mylibrary.widget.UICToast;


public class PhoneFilter extends InputFilter {

    private static final String TAG = "PhoneFilter";

    @Override
    public Filter emptyFilter(String content) {
        if(isMatch){
            return this;
        }
        isMatch = TextUtils.isEmpty(content);
        if(isMatch){
            UICToast.instance().showNormalToast("请输入手机号");
        }
        return this;
    }

    @Override
    public Filter formatFilter(String content) {
        if(isMatch){
            return this;
        }
        String passwordReg = "^1\\d{10}$";
        isMatch = !content.matches(passwordReg);
        if(isMatch){
            UICToast.instance().showNormalToast("请输入合法的手机号！");
        }
        return this;
    }

    public boolean checkPhone(String content){
        String passwordReg = "^1\\d{10}$";
        return !content.matches(passwordReg);
    }

    @Override
    public boolean filter() {
        return isMatch;
    }
}
