package com.example.mylibrary.arouter;

/**
 * 统一管理路由path，可以避免path重复
 * 注意：
 *  _____________________________________
 * #
 * # 不同module的路由路径的一级命名不能相同，否则会报错哦！
 * # 同一个标识不能用于不同的目标(Activity)
 * #______________________________________
 *
 * */
public final class ARouterPath {

    //app
    public static final String APP_HOME = "/app_home/activity/HomeActivity";
    public static final String APP_SETING = "/seting/SetingActivity";
    public static final String APP_SETING_MAIN = "/seting/MainActivity";


}
