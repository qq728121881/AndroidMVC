package com.example.mylibrary.tool.verify.filter;

import android.text.TextUtils;

import com.example.mylibrary.widget.UICToast;


public class AccountFilter extends InputFilter {

    public AccountFilter(){
        isMatch = false;//初始化正确
    }

    @Override
    public Filter emptyFilter(String content) {
        if(isMatch){
            return this;
        }
        isMatch = TextUtils.isEmpty(content);
        if(isMatch){
            UICToast.instance().showNormalToast("请输入用户名");
        }
        return this;
    }

    @Override
    public Filter formatFilter(String content) {

        return this;
    }

    @Override
    public boolean filter() {
        return isMatch;
    }
}
