package com.example.mylibrary.net.util;


/**
 * @作者 邸昌顺
 * @时间 2018/12/22 13:35
 * @描述
 */
public interface NetListener {
    void onSuccess(Object result);
    void onError(Throwable e);
}
