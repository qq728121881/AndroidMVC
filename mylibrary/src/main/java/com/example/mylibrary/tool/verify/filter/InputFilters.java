package com.example.mylibrary.tool.verify.filter;

import android.text.TextUtils;
import android.util.Log;

import com.example.mylibrary.widget.UICToast;


/**
 * 判断及提示
 * 使用的时候按照顺序，执行Filter方法，就会按照顺序提示不符合要求的内容
 * */
public class InputFilters extends Filter{

    private static final String TAG = "InputFilters";

    private boolean isMatch;

    public InputFilters(){
        isMatch = true;
    }

    /**
     * 密码
     * */
    public InputFilters passwordFilter(String password, String... notices){

        Log.e(TAG,"[passwordFilter] password is " + password);

        if(isMatch){
            if(TextUtils.isEmpty(password)){
                UICToast.instance().showNormalToast((notices.length == 0) ? "请输入密码" : notices[0]);
                isMatch = false;
            }else if(password.length() < 6 && !(notices.length > 0)){
                UICToast.instance().showNormalToast((notices.length == 0) ? "密码不符合规则" : notices[1]);
                isMatch = false;
            }else {
                //String reg = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{6,50})$";//6-50位密码限制，同时包含数字、字母
                String reg = "^([a-zA-Z0-9]{6,50})$";
                isMatch = password.matches(reg);
                if(!isMatch){
                    UICToast.instance().showNormalToast((notices.length == 0) ? "密码不符合规则" : notices[1]);
                }
            }
        }

        return this;
    }

    /**
     * 用户名
     * */
    public InputFilters idFilter(String id){

        Log.e(TAG,"[passwordFilter] id is " + id);

        if(isMatch){
            if(TextUtils.isEmpty(id)){
                UICToast.instance().showNormalToast("请输入账号");
                isMatch = false;
            }
        }

        return this;
    }

    /**
     * 手机号
     * */
    public InputFilters phoneFilter(String phone){

        //判断字符串是否符合手机号码格式
        // 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
        // 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
        // 电信号段: 133,149,153,170,173,177,180,181,189
        // @param str
        // @return 待检测的字符串
        // String reg = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";

        Log.e(TAG,"[passwordFilter] phone is " + phone);

        if(isMatch){
            if(TextUtils.isEmpty(phone)){
                UICToast.instance().showNormalToast("请输入手机号");
                isMatch = false;
            }else{
                String reg = "^1\\d{10}$";
                isMatch = phone.matches(reg);
                if(!isMatch){
                    UICToast.instance().showNormalToast("请输入合法的手机号");
                }
            }
        }
        return this;
    }

    /**
     * 验证码
     * */
    public InputFilters verifyCodeFilter(String code){

        Log.e(TAG,"[passwordFilter] verify code is " + code);

        if(isMatch){
            if(TextUtils.isEmpty(code)){
                UICToast.instance().showNormalToast("请输入验证码");
                isMatch = false;
            }else if(code.length() != 4){
                isMatch = false;
                UICToast.instance().showNormalToast("请输入正确的验证码");
            }
        }

        return this;
    }

    @Override
    public boolean filter() {
        return !isMatch;
    }
}
