package com.example.mylibrary.tool.verify.filter;

import android.text.TextUtils;

import com.example.mylibrary.widget.UICToast;


public class VerifyCodeFilter extends InputFilter {

    private static final String TAG = "VerifyCodeFilter";

    @Override
    public Filter emptyFilter(String content) {
        if(isMatch){
            return this;
        }
        isMatch = TextUtils.isEmpty(content);
        if(isMatch){
            UICToast.instance().showNormalToast("请输入验证码");
        }
        return this;
    }

    @Override
    public Filter formatFilter(String content) {
        if(isMatch){
            return this;
        }
        String passwordReg = "^\\d{4}$";
        isMatch = !content.matches(passwordReg);
        if(isMatch){
            UICToast.instance().showNormalToast("请输入正确的验证码！");
        }
        return this;
    }

    @Override
    public boolean filter() {
        return isMatch;
    }
}
