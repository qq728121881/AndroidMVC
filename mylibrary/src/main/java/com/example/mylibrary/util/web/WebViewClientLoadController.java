package com.example.mylibrary.util.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mylibrary.tool.log.DLog;


public class WebViewClientLoadController {

    private static final String TAG = "WebViewClientLoadController";

    private boolean hasReceivedError;
    WebLoadListener mWebLoadListener;
    WebControlListener mWebControlListener;

    public WebViewClientLoadController(WebLoadListener webLoadListener, WebControlListener webControlListener){
        this.mWebLoadListener = webLoadListener;
        this.mWebControlListener = webControlListener;
    }

    private void setReceivedError(boolean hasReceivedError){
        this.hasReceivedError = hasReceivedError;
    }

    public WebViewClient getDefaultWebViewClient(){
        return new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                DLog.i(TAG, "[onPageStarted]");
                setReceivedError(false);
                mWebLoadListener.onWebLoadStart();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                DLog.i(TAG, "[onPageFinished]");
                if(hasReceivedError){
                    return;
                }
                mWebLoadListener.onWebLoadEnd();
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
                DLog.i(TAG, "[doUpdateVisitedHistory]");
                if (mWebControlListener.toClearHistory()) {//清空历史记录
                    mWebControlListener.setClearHistory(false);
                    view.clearHistory();
                }
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                if(TextUtils.equals(view.getTitle(), "圈子详情")){
                    mWebLoadListener.onWebLoadEnd();
                }
                DLog.i(TAG, "[onLoadResource] [" + view.getTitle() + "] "+ url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                setReceivedError(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    DLog.e(TAG, "[onReceivedError] [M] " + error.getErrorCode() + ", " + error.getDescription());
                    if(request.isForMainFrame()){
                        mWebLoadListener.onWebLoadError(error.getErrorCode(), error.getDescription().toString());
                    }
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                setReceivedError(true);
                DLog.e(TAG, "[onReceivedError] " + errorCode + ", " + description);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    mWebLoadListener.onWebLoadError(errorCode, description);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                DLog.i("shouldOverrideUrlLoading " + url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String url = request.getUrl().toString();
                    DLog.i("shouldOverrideUrlLoading request " + url);
                    view.loadUrl(url);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        };
    }


}
