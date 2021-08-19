package com.example.mylibrary.net.util;

import android.content.Intent;
import android.os.Handler;


import com.example.mylibrary.tool.AppTool;
import com.example.mylibrary.widget.UICToast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @作者 邸昌顺
 * @时间 2019/1/8 16:49
 * @描述
 */
public class StateInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if(response.code() == 401){ //账号过期
            reLogin();
        }else if(response.code() >= 500){//接口错误
            showToast("哎呀！程序出了点小状况，稍后再试吧~");
            /*try {
                BaseResponse baseResponse = GsonUtils.toObject(response.body().string(), BaseResponse.class);
                showToast(baseResponse.message);
            }catch (Exception e){
                showToast("500");
            }*/
        }
        return response;
    }

    private static void reLogin(){
        Handler handler = new Handler(AppTool.getInstance().getApplication().getMainLooper());
        handler.post( ()-> {
            UICToast.instance().showNormalToast("您的账号已经退出，请重新登录");
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction("com.module.login.presenter.activity.LoginActivity");
            AppTool.getInstance().getApplication().startActivity(intent);
        });
    }

    private static void showToast(String message){
        Handler handler = new Handler(AppTool.getInstance().getApplication().getMainLooper());
        handler.post(()-> UICToast.instance().showNormalToast(message));
    }
}
