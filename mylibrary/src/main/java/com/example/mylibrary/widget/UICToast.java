package com.example.mylibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.R;
import com.example.mylibrary.tool.AppTool;


public class UICToast {

    private static volatile UICToast sToast = null;

    private UICToast(int type) {
        if (type == 1) {
            init();
        }
    }

    public static UICToast instance() {
        if (sToast == null) {
            synchronized (UICToast.class) {
                if (sToast == null) {
                    sToast = new UICToast(1);
                }
            }
        }
        return sToast;
    }

    public static UICToast bindInstance() {
        if (sToast == null) {
            synchronized (UICToast.class) {
                if (sToast == null) {
                    sToast = new UICToast(2);
                }
            }
        }
        return sToast;
    }

    private Toast mToast;
    private TextView mTvMsg;
    private Toast mBindToast;
    private Toast mViewToast;

    private void init() {
//        LayoutInflater inflater = LayoutInflater.from(AppTool.getInstance().getApplication());
//        View initToastView = inflater.inflate(R.layout.view_toast_common, null);
//        mTvMsg = initToastView.findViewById(R.id.tv_toast_message);
//        if (null == mToast) {
//            mToast = new Toast(AppTool.getInstance().getApplication());
//        }
//        mToast.setDuration(Toast.LENGTH_SHORT);
//        mToast.setView(initToastView);
    }

    public void showNormalToast(String msg) {
        if (mToast == null) {
            init();
        }

        if (!TextUtils.isEmpty(msg)) {
            cancelBindToast();
            cancelViewToast();

            mTvMsg.setText(msg);
            mToast.show();
        }
    }

    public void showBindToast(Activity activity, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

//        cancelNormalToast();
//        cancelBindToast();
//        cancelViewToast();
//
//        LayoutInflater inflater = LayoutInflater.from(activity);
//        View initToastView = inflater.inflate(R.layout.view_toast_common, null);
//        TextView mBindTvMsg = initToastView.findViewById(R.id.tv_toast_message);
//        mBindToast = new Toast(activity);
//        mBindToast.setDuration(Toast.LENGTH_SHORT);
//        mBindToast.setView(initToastView);
//
//        mBindTvMsg.setText(msg);
//        mBindToast.show();
    }

    public void showViewToast(Context context, View view) {
        if (view == null) {
            return;
        }

        cancelNormalToast();
        cancelBindToast();
        cancelViewToast();

        mViewToast = new Toast(context);
        mViewToast.setDuration(Toast.LENGTH_SHORT);//默认较短时间显示
        mViewToast.setView(view);
        mViewToast.show();
    }

    public void cancelBindToast() {
        if (mBindToast != null) {
            mBindToast.cancel();
            mBindToast = null;
        }
    }

    public void cancelNormalToast() {
        if (null != mToast) {
            mToast.cancel();
        }
    }

    public void cancelViewToast() {
        if (mViewToast != null) {
            mViewToast.cancel();
            mViewToast = null;
        }
    }
}
