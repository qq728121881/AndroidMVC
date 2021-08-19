package com.example.mylibrary.tool.verify.filter;

import android.text.TextUtils;

import com.example.mylibrary.widget.UICToast;


public class PasswordFilter extends InputFilter {

    private static final String TAG = "PasswordFilter";

    public PasswordFilter(){
        isMatch = false;
    }

    @Override
    public Filter emptyFilter(String content){
        if(isMatch){
            return this;
        }
        isMatch = TextUtils.isEmpty(content);
        if(isMatch){
            UICToast.instance().showNormalToast("请输入密码");
        }
        return this;
    }

    @Override
    public Filter formatFilter(String content){
        if(isMatch){
            return this;
        }
        String passwordReg = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{8,50})$";
        isMatch = !content.matches(passwordReg);
        if(isMatch){
            UICToast.instance().showNormalToast("密码需包含字母与数字，且密码长度需大于等于8位。");
        }
        return this;
    }

    @Override
    public boolean filter() {
        return isMatch;
    }
}
