package com.example.mylibrary.util.localstore;

/**
 * 约束当前用户保存的偏好信息
 * 统一调用方式
 * */
public interface UserPreferences {

    /**
     * 获取：当前用户在本设备上第一次登录
     * */
    boolean isUserFirstLogin();

    /**
     * 设置：当前用户已经登录过
     * */
    void setUserHadLogin();

    /**
     * 获取：APP第一次打开
     * */
    boolean isFirstUse();

    /**
     * 设置：APP打开过
     * */
    void setHadUsed();

    /**
     * 获取：是否已经登录了用户
     * */
    boolean isLogin();

    /**
     * 设置：用户登录了
     * */
    void setLogin();

    /**
     * 设置：用户退出了登录
     * */
    void logout();

    /**
     * 获取：是否需要显示协议弹窗
     * */
    boolean showProtocol();

    /**
     * 设置：显示协议弹窗
     * */
    void setProtocolShown();

    /**
     * 获取：上一个登录的账号
     * */
    String getLoginUserName();

    /**
     * 账号
     * */
    void setLoginUserName(String userName);
}
