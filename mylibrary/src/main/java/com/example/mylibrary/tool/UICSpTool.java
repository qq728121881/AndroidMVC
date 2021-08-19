package com.example.mylibrary.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.util.Map;

/**
 * sharePreference 方法类
 * 提供sp通用方法
 * 单例模式避免多次初始化
 * */
public class UICSpTool {

    private static final String SP_FILE_NAME = "share_file";

    private SharedPreferences mSharedPreferences;
    private ArrayMap<String, Object> cacheValueMap;//实现内存缓存
    private ArrayMap<String, Object> cacheUpdateValueMap;//保存更新了的数据

    private static volatile UICSpTool sUICSpTool = null;

    private UICSpTool(){}

    public static UICSpTool getInstance(){
        if(sUICSpTool == null){
            synchronized (UICSpTool.class){
                if(sUICSpTool == null){
                    sUICSpTool = new UICSpTool();
                }
            }
        }
        return sUICSpTool;
    }

    /**
     * 必须要在application中调用这个方法，实现初始化
     * 或者，要求整个APP活动中，只能调用一次该方法
     * */
    public void init(Context context){
        mSharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        cacheValueMap = new ArrayMap<>();
        cacheUpdateValueMap = new ArrayMap<>();
    }

    /**
     * 添加数据
     * */
    public UICSpTool put(String key, Object value){
        cacheValueMap.put(key, value);
        cacheUpdateValueMap.put(key, value);
        return this;
    }

    /**
     * 每次更新数据之后，必须要调用下面的方法才能把数据保存到SharePreference文件中
     * */
    public void apply(){
        if(cacheUpdateValueMap != null && cacheUpdateValueMap.size() > 0){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            for (Map.Entry<String, Object> entry : cacheUpdateValueMap.entrySet()) {
                putValue(editor, entry.getKey(), cacheUpdateValueMap.get(entry.getKey()));
            }
            cacheUpdateValueMap.clear();
            editor.apply();
        }
    }

    private void putValue(SharedPreferences.Editor editor, String key, Object object){
        String objectClassName = object.getClass().getSimpleName();
        switch (objectClassName){
            case "String":
                editor.putString(key, (String) object);
                break;
            case "Integer":
                editor.putInt(key, (Integer) object);
                break;
            case "Long":
                editor.putLong(key, (Long) object);
                break;
            case "Float":
                editor.putFloat(key, (Float) object);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) object);
                break;
        }
    }

    /**
     * 从SharePreference文件中获取String类型数据
     * */
    public String get(String key, String defaultValue){
        if(cacheValueMap.containsKey(key)){
            if(cacheValueMap.get(key) instanceof String){
                return (String) cacheValueMap.get(key);
            }
        }
        String data = mSharedPreferences.getString(key, defaultValue);
        cacheValueMap.put(key, data);
        return data;
    }

    /**
     * 从SharePreference文件中获取int类型数据
     * */
    public int get(String key, int defaultValue){
        if(cacheValueMap.containsKey(key)){
            if(cacheValueMap.get(key) instanceof Integer){
                return (Integer) cacheValueMap.get(key);
            }
        }
        int data = mSharedPreferences.getInt(key, defaultValue);
        cacheValueMap.put(key, data);
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 从SharePreference文件中获取long类型数据
     * */
    public long get(String key, long defaultValue){
        if(cacheValueMap.containsKey(key)){
            if(cacheValueMap.get(key) instanceof Long){
                return (Long) cacheValueMap.get(key);
            }
        }
        long data = mSharedPreferences.getLong(key, defaultValue);
        cacheValueMap.put(key, data);
        return mSharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 从SharePreference文件中获取float类型数据
     * */
    public float get(String key, float defaultValue){
        if(cacheValueMap.containsKey(key)){
            if(cacheValueMap.get(key) instanceof Float){
                return (Float) cacheValueMap.get(key);
            }
        }
        float data = mSharedPreferences.getFloat(key, defaultValue);
        cacheValueMap.put(key, data);
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 从SharePreference文件中获取boolean类型数据
     * */
    public boolean get(String key, boolean defaultValue){
        if(cacheValueMap.containsKey(key)){
            if(cacheValueMap.get(key) instanceof Boolean){
                return (Boolean) cacheValueMap.get(key);
            }
        }
        boolean data = mSharedPreferences.getBoolean(key, defaultValue);
        cacheValueMap.put(key, data);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 清除缓存数据
     * 切换用户的时候，需要清除掉map中缓存的数据
     * 避免不同用户数据错乱
     * */
    public void clearCache(){
        if(cacheValueMap != null){
            cacheValueMap.clear();
        }
    }

    /**
     * 删除对应key值得数据
     * */
    public void remove(String key){
        if(cacheValueMap != null){
            cacheValueMap.remove(key);
        }
        if(mSharedPreferences.contains(key)){
            mSharedPreferences.edit().remove(key).apply();
        }
    }

    /**
     * 清除所有SharePreference数据
     * */
    public void clear(){
        clearCache();
        Map<String, ?> map = mSharedPreferences.getAll();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for(Map.Entry<String, ?> entry : map.entrySet()){
            editor.remove(entry.getKey());
        }
        editor.apply();
    }
}
