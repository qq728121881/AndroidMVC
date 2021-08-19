package com.example.mylibrary.widget.text;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.lang.ref.WeakReference;

public class UICTimeDownTextView extends AppCompatTextView {

    private long mCountDownMillis = 60000;   //倒计时时间
    private long mLastMillis;   //剩余倒计时时间
    private long mIntervalMillis = 1000;   //间隔时间差(两次发送handler)
    private static final int MSG_WHAT_START = 10010;   //开始倒计时code
    private int usableColorId = android.R.color.holo_blue_light;    //可用状态下字体颜色Id
    private int unusableColorId = android.R.color.darker_gray;      //不可用状态下字体颜色Id
    private Handler mHandler;

    public UICTimeDownTextView(Context context) {
        this(context, null);
    }

    public UICTimeDownTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UICTimeDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mHandler = new WeakHandler(this);
    }

    private static class WeakHandler extends Handler{

        private WeakReference<UICTimeDownTextView> mTimeDownTextViewReference;

        private WeakHandler(UICTimeDownTextView view) {
            mTimeDownTextViewReference = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mTimeDownTextViewReference.get() == null){
                return;
            }
            UICTimeDownTextView uicTimeDownTextView = mTimeDownTextViewReference.get();
            switch (msg.what) {
                case MSG_WHAT_START:
                    if (uicTimeDownTextView.mLastMillis > 0) {
                        uicTimeDownTextView.setUsable(false);
                        uicTimeDownTextView.mLastMillis -= uicTimeDownTextView.mIntervalMillis;
                        uicTimeDownTextView.mHandler.sendEmptyMessageDelayed(MSG_WHAT_START, uicTimeDownTextView.mIntervalMillis);
                    } else {
                        uicTimeDownTextView.setUsable(true);
                    }
                    break;
            }
        }
    }

    private void setUsable(boolean usable) {
        if (usable) {
            //可用
            if (!isClickable()) {
                setClickable(true);
                setTextColor(getResources().getColor(usableColorId));
                setText("重新获取");
            }
        } else {
            //不可用
            if (isClickable()) {
                setClickable(false);
                setTextColor(getResources().getColor(usableColorId));
            }
            setText((mLastMillis / 1000) + "s");
        }
    }

    /**
     * 设置倒计时颜色
     *
     * @param usableColorId   可用状态下的颜色
     * @param unusableColorId 不可用状态下的颜色
     */
    public void setCountDownColor(@ColorRes int usableColorId, @ColorRes int unusableColorId) {
        this.usableColorId = usableColorId;
        this.unusableColorId = unusableColorId;
    }

    /**
     * 设置倒计时时间
     *
     * @param millis 毫秒值
     */
    public void setCountDownMillis(long millis) {
        mCountDownMillis = millis;
    }

    /**
     * 开始倒计时
     */
    public void start() {
        mHandler.removeMessages(MSG_WHAT_START);
        mLastMillis = mCountDownMillis;
        mHandler.sendEmptyMessage(MSG_WHAT_START);
    }

    /**
     * 重置倒计时
     */
    public void reset() {
        setClickable(true);
        setTextColor(getResources().getColor(usableColorId));
        setText("获取验证码");
        mHandler.removeMessages(MSG_WHAT_START);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(MSG_WHAT_START);
        mHandler = null;
    }
}
