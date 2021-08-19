package com.example.mylibrary.net;



import com.example.mylibrary.net.api.BusinessService;
import com.example.mylibrary.net.api.CommonService;
import com.example.mylibrary.net.api.EvaluateService;
import com.example.mylibrary.net.modules.NetModule;

import io.reactivex.Observable;

public class DataManager {

    private final BusinessService businessService;
    private final CommonService commonService;
    private final EvaluateService evaluateService;

    private static volatile DataManager INSTANCE = null;

    private DataManager() {
        commonService = NetModule.providesLoginService();
        businessService = NetModule.providesCommonService();
        evaluateService = NetModule.providesEvaluateService();
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DataManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataManager();
                }
            }
        }
        return INSTANCE;
    }

    //返回登录用户信息
    public Observable<String> login(String requestLogin) {
        return commonService.login(requestLogin);
    }
//
//    //验证码登录
//    public Observable<ResponseVerifyCodeLogin> verifyCodeLogin(RequestVerifyCodeLogin requestVerifyCodeLogin) {
//        return commonService.verifyCodeLogin(requestVerifyCodeLogin);
//    }


}