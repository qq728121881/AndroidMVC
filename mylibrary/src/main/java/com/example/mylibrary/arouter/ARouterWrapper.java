package com.example.mylibrary.arouter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.example.mylibrary.tool.log.DLog;

public final class ARouterWrapper {

    public static abstract class AbsNavigationCallbackWrapper implements NavigationCallback {

        private final Context mContext;

        public AbsNavigationCallbackWrapper(Context context){
            this.mContext = context;
        }

        @Override
        public void onFound(Postcard postcard) {
            DLog.e("onFound");
        }

        /**
         * 跳转失败
         * */
        @Override
        public void onLost(Postcard postcard) {
            Toast.makeText(mContext, "未发现目标Activity！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onArrival(Postcard postcard) {
            DLog.e("onArrival");
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            DLog.e("onInterrupt");
        }
    }

    public static class NavigationCallbackWrapper implements NavigationCallback {

        private final Context mContext;

        public NavigationCallbackWrapper(Context context){
            this.mContext = context;
        }

        @Override
        public void onFound(Postcard postcard) {
            DLog.e("onFound");
        }

        /**
         * 跳转失败
         * */
        @Override
        public void onLost(Postcard postcard) {
            DLog.e( "onLost");
            Toast.makeText(mContext, "未发现目标Activity！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onArrival(Postcard postcard) {
            DLog.e( "onArrival");
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            DLog.e( "onInterrupt");
        }
    }

    /**
     * 封装ARouter，统一处理导航回调
     *
     * @param context 当前跳转要用到的上下文
     * @param postcard ARouter跳转传值
     * */
    public static void navigation(Context context, Postcard postcard){
        postcard.navigation(context, new NavigationCallbackWrapper(context));
    }

    /**
     * 封装ARouter，统一处理导航回调
     *
     * @param context 当前跳转要用到的上下文
     * @param postcard ARouter跳转传值
     * */
    public static void navigation(Context context, Postcard postcard, NavigationCallback navigationCallback){
        postcard.navigation(context, navigationCallback);
    }

    /**
     * 封装ARouter，统一处理导航回调
     *
     * @param activity 当前跳转要用到的上下文
     * @param postcard ARouter跳转传值
     * */
    public static void navigation(Activity activity, Postcard postcard, int requestCode, NavigationCallback navigationCallback){
        postcard.navigation(activity, requestCode, navigationCallback);
    }

    /**
     * 封装ARouter，统一处理导航回调
     *
     * @param activity 当前跳转要用到的上下文
     * @param postcard ARouter跳转传值
     * */
    public static void navigation(Activity activity, Postcard postcard, int requestCode){
        postcard.navigation(activity, requestCode, new NavigationCallbackWrapper(activity));
    }

}
