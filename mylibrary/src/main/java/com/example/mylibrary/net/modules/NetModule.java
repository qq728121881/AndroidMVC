package com.example.mylibrary.net.modules;


import com.example.mylibrary.config.APIConfig;
import com.example.mylibrary.net.api.BusinessService;
import com.example.mylibrary.net.api.CommonService;
import com.example.mylibrary.net.api.EvaluateService;
import com.example.mylibrary.net.util.LocalCookieJar;
import com.example.mylibrary.net.util.SSLSocketClient;
import com.example.mylibrary.net.util.StateInterceptor;
import com.example.mylibrary.tool.log.DLog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mylibrary.config.PathConfig.HTTP_CACHE_PATH;


public class NetModule {

    private static final String TAG = "NetModule";

    private static BusinessService businessService;
    private static CommonService commonService;
    private static EvaluateService evaluateService;

    static Interceptor providesInterceptor() { //设置头部拦截器，添加请求头内容
        return chain -> {
            String token =" UserCacheStore.getInstance().getToken()";
            int maxAge = 5;
            Request.Builder builder = chain.request().newBuilder();
            builder.url(chain.request().url())
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "public, max-age=" + maxAge)
                    .addHeader("Connection", "close")
                    .addHeader("authorization", token)
                    //.addHeader("authorization", "980982e820ff11e89286fa163e29292b_7944c924c3104f4f9c312dc827361881")
                    .build();

            DLog.e("test", token);
            return chain.proceed(builder.build());
        };
    }

    static Cache providesCache() {
        return new Cache(new File(HTTP_CACHE_PATH), 1024 * 100 * 1024);
    }

    public static BusinessService providesCommonService() {
        if (businessService == null) {
            businessService = getCommonRetrofit(providesInterceptor()).create(BusinessService.class);
        }
        return businessService;
    }

    public static CommonService providesLoginService() {
        if (commonService == null) {
            commonService = getLoginRetrofit(providesInterceptor()).create(CommonService.class);
        }
        return commonService;
    }

    public static EvaluateService providesEvaluateService() {
        if (evaluateService == null) {
            evaluateService = getEvaluateRetrofit(providesInterceptor()).create(EvaluateService.class);
        }
        return evaluateService;
    }

    private static Retrofit getEvaluateRetrofit(Interceptor interceptor) {
        return new Retrofit.Builder()
                .baseUrl(APIConfig.SERVER_EVALUATE_ADDRESS)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static Retrofit getLoginRetrofit(Interceptor interceptor) {
        return new Retrofit.Builder()
                .baseUrl(APIConfig.SERVER_APP_ADDRESS)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static Retrofit getCommonRetrofit(Interceptor interceptor) {
        return new Retrofit.Builder()
                .baseUrl(APIConfig.SERVER_WEB_ADDRESS)
                .client(getOkHttpClientLog())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addInterceptor(providesInterceptor())
                .cookieJar(LocalCookieJar.getInstance())    //为OkHttp设置自动携带Cookie的功能
                .addInterceptor(new StateInterceptor())     //接口状态拦截器
                .addInterceptor(new HttpLoggingInterceptor(message-> DLog.d(TAG, message)).setLevel(HttpLoggingInterceptor.Level.BODY)) //加日志
                .connectTimeout(20, TimeUnit.SECONDS)   //设置超时
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)  //错误重连
                .build();
//        return OkHttpManager.getInstance().getOkHttpClient();
    }

    /***
     * 上传文件打印全部日志会导致OOM，这里只打印Headers级别的日志；
     * */
    public static OkHttpClient getOkHttpClientLog() {
        return new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addNetworkInterceptor(providesInterceptor())
                .cookieJar(LocalCookieJar.getInstance())    //为OkHttp设置自动携带Cookie的功能
                .addInterceptor(new StateInterceptor())     //接口状态拦截器
                .addInterceptor(new HttpLoggingInterceptor(message-> DLog.d(TAG, message)).setLevel(HttpLoggingInterceptor.Level.HEADERS)) //加日志
                .connectTimeout(20, TimeUnit.SECONDS)   //设置超时
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)  //错误重连
                .build();
    }

}
