package com.example.mylibrary.net.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 注意了解学习retrofit2的使用
 */
public interface CommonService {
//
    //用户登录请求api
    @POST("/xxx")
    Observable<String> login(@Body String requestLogin);
//
//    //验证码登录
//    @POST("/xxx")
//    @Headers({
//            "apt-version:3.2"
//    })
//    Observable<ResponseVerifyCodeLogin> verifyCodeLogin(@Body RequestVerifyCodeLogin requestRegister);
//
//
//    //重置密码 {"mobile": "string","newPassword": "string","smsCode": "string"}
//    @PUT("/xx")
//    Observable<ResponseUpdateUserPassword> updateUserPassword(@Body RequestUpdateUserPassword requestUpdateUserPassword);
//
//    //注册 {"authority": "ROLE_Student","mobile": "string","password": "string","smsCode": "string","userManagement": 0}
//    @POST("/xx")
//    Observable<ResponseRegister> register(@Body RequestRegister requestRegister);
//
//    @GET("/xx")
//    Observable<ResponseSearchBookStore> getAllFreeBooks(@Query("condition") String condition, @Query("indexPage") int indexPage, @Query("pageSize") int pageSize);


}
