package com.example.mylibrary.util.web;

public interface WebLoadListener {

    void onWebLoadStart();

    void onWebLoadEnd();

    void onWebLoadError(int code, String errorDescription);
}
