/*
package com.example.mylibrary.util.localstore;

import android.app.Application;

import com.app.base.tool.log.DLog;
import com.tencent.mmkv.MMKV;

*/
/**
 * 存储一些本地要使用的参数
 * *//*

public class LocalStore implements StorePreferences, UserPreferences {

    private static final String TAG = "LocalStore";

    private static volatile LocalStore instance = null;

    private LocalStore(){}

    public static LocalStore getInstance() {
        if (instance == null) {
            synchronized (LocalStore.class) {
                if (instance == null) {
                    instance = new LocalStore();
                }
            }
        }
        return instance;
    }

    */
/**
     * 用户偏好
     * *//*

    private UserPreferences userPreferences;

    */
/**
     * 需要在Application中去初始化
     * 返回自己，是为了方便链式调用
     * *//*

    public LocalStore init(Application application) {
        String rootDir = MMKV.initialize(application);
        DLog.e(TAG, "rootDir:" + rootDir);
        return this;
    }

    */
/**
     * 切换用户偏好
     * 可以支持多个偏好对象
     * *//*

    public void switchPreference(UserPreferences preferences) {
        userPreferences = preferences;
    }

    private MMKV getStore() {
        return MMKV.defaultMMKV();
    }

    @Override
    public void putString(String key, String value) {
        getStore().encode(key, value);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return getStore().decodeString(key, defaultValue);
    }

    @Override
    public void putInt(String key, int value) {
        getStore().encode(key, value);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return getStore().decodeInt(key, defaultValue);
    }

    @Override
    public void putLong(String key, long value) {
        getStore().encode(key, value);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return getStore().decodeLong(key, defaultValue);
    }

    @Override
    public void putFloat(String key, long value) {
        getStore().encode(key, value);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return getStore().decodeFloat(key, defaultValue);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        getStore().encode(key, value);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return getStore().decodeBool(key, defaultValue);
    }

    @Override
    public void remove(String key) {
        getStore().removeValueForKey(key);
    }

    @Override
    public void remove(String[] key) {
        getStore().removeValuesForKeys(key);
    }

    @Override
    public void clear() {
        getStore().clearAll();
    }

    @Override
    public boolean isUserFirstLogin() {
        return userPreferences.isUserFirstLogin();
    }

    @Override
    public void setUserHadLogin() {
        userPreferences.setUserHadLogin();
    }

    @Override
    public boolean isFirstUse() {
        return userPreferences.isFirstUse();
    }

    @Override
    public void setHadUsed() {
        userPreferences.setHadUsed();
    }

    @Override
    public boolean isLogin() {
        return userPreferences.isLogin();
    }

    @Override
    public void setLogin() {
        userPreferences.setLogin();
    }

    @Override
    public void logout() {
        userPreferences.logout();
    }

    @Override
    public boolean showProtocol() {
        return userPreferences.showProtocol();
    }

    @Override
    public void setProtocolShown() {
        userPreferences.setProtocolShown();
    }

    @Override
    public String getLoginUserName() {
        return userPreferences.getLoginUserName();
    }

    @Override
    public void setLoginUserName(String userName) {
        userPreferences.setLoginUserName(userName);
    }
}
*/
