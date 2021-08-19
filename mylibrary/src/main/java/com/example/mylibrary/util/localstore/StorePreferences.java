package com.example.mylibrary.util.localstore;

/**
 * 统一API样式
 * */
public interface StorePreferences {

    void putString(String key, String value);

    String getString(String key, String defaultValue);

    void putInt(String key, int value);

    int getInt(String key, int defaultValue);

    void putLong(String key, long value);

    long getLong(String key, long defaultValue);

    void putFloat(String key, long value);

    float getFloat(String key, float defaultValue);

    void putBoolean(String key, boolean value);

    boolean getBoolean(String key, boolean defaultValue);

    void remove(String key);

    void remove(String[] key);

    void clear();
}
