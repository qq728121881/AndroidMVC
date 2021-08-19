package com.example.mylibrary.config;


/**
 * 服务器环境配置
 * */
public class APIConfig {
    public static String SERVER_APP_ADDRESS = BuildConfig.SERVER_IP + BuildConfig.PORT_APP;
    public static String SERVER_WEB_ADDRESS = BuildConfig.SERVER_IP + BuildConfig.PORT_WEB;
    public static String SERVER_WEB_FILE_ADDRESS = BuildConfig.SERVER_IP + BuildConfig.PORT_WEB + "/files/";
    public static String SERVER_EINK_ADDRESS = BuildConfig.SERVER_IP + BuildConfig.PORT_EINK;
    public static String SERVER_EINK_FILE_ADDRESS = BuildConfig.SERVER_IP + BuildConfig.PORT_EINK + "/files/";
    public static String SERVER_UPLOAD_URI = BuildConfig.SERVER_IP_FILE + BuildConfig.PORT_EINK + "/rest/logFile";
    public static final String SERVER_EVALUATE_ADDRESS = BuildConfig.SERVER_IP_EVAL + BuildConfig.PORT_EVAL;
}
