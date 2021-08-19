package com.example.mylibrary.help;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

public final class EditTextPasswordTransHelper {

    private boolean isPasswordTxtShow;

    private EditText et;
    private ImageView ivNotice;
    private int resIdNoticeCheck;
    private int resIdNoticeNormal;

    public EditTextPasswordTransHelper(EditText et, ImageView ivNotice, int resIdNoticeCheck, int resIdNoticeNormal) {
        this.et = et;
        this.ivNotice = ivNotice;
        this.resIdNoticeCheck = resIdNoticeCheck;
        this.resIdNoticeNormal = resIdNoticeNormal;
    }

    public void setPasswordTxtShow(boolean isShow){
        isPasswordTxtShow = isShow;
        if (isPasswordTxtShow) {//显示
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivNotice.setImageResource(resIdNoticeCheck);
        } else {//隐藏
            et.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivNotice.setImageResource(resIdNoticeNormal);
        }
        if(TextUtils.isEmpty(et.getText().toString().trim())){
            et.setSelection(0);
        }else{
            et.setSelection(et.getText().toString().trim().length());
        }
    }

    public void changePasswordTxtShow(){
        isPasswordTxtShow = !isPasswordTxtShow;
        if (isPasswordTxtShow) {//显示
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivNotice.setImageResource(resIdNoticeCheck);
        } else {//隐藏
            et.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivNotice.setImageResource(resIdNoticeNormal);
        }
        if(TextUtils.isEmpty(et.getText().toString().trim())){
            et.setSelection(0);
        }else{
            et.setSelection(et.getText().toString().trim().length());
        }
    }

    public boolean isPasswordTxtShow(){
        return isPasswordTxtShow;
    }
}
