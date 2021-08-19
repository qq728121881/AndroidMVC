package com.example.mylibrary.config;


import com.example.mylibrary.util.platform.PlatFormQ;

public class PathConfig {

    //基础文件夹
    private static String USER_URI = PlatFormQ.USER_URI;

    //图书保存的路径
    public static String DOWNLOAD_PATH = USER_URI + "book/";

    //网络请求缓存路径
    public static String HTTP_CACHE_PATH = USER_URI + ".netCache/";

    //上传图片保存的路径
    public static String UPLOAD_IMAGE_PATH = USER_URI + "upload_image/";

    //埋点日志保存的路径
    public static String UPLOAD_BEHAVIOR_LOG = USER_URI + "behavior_log/";

}
