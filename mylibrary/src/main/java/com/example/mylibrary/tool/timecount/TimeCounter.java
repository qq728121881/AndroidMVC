package com.example.mylibrary.tool.timecount;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.mylibrary.tool.AppTool;
import com.example.mylibrary.tool.log.DLog;

import java.lang.ref.WeakReference;

/**
 * 计时工具，实现很简单：
 * handler的计时
 * */
public class TimeCounter implements ITimeCounterDeal{

    private final long total_time;
    private long current_time;
    private final TimeCountWatcher mTimeCountWatcher;

    /**
     * 用来接收发送的消息，并处理
     * */
    private WeakHandler mWeakHandler;
    /**
     * 计时handler，每过一定时间 {@see TIME_DELAY}发送消息给handler
     * */
    private Handler mProgressHandler;
    private Runnable mProgressRunnable;

    private static final long TIME_DELAY = 1000L;//毫秒
    private static final int DOING = 1;
    private static final int PAUSE = 2;
    private static final int STOP = 3;

    public TimeCounter(long total_time, TimeCountWatcher watcher){
        this.total_time = total_time;
        this.mTimeCountWatcher = watcher;
        this.current_time = total_time;
    }

    private void onProgress() {
        current_time -= TIME_DELAY;
        if(current_time <= 0){
            current_time = 0;
            mTimeCountWatcher.onProgress(current_time, TIME_DELAY, total_time);
            mTimeCountWatcher.onEnd();
            onStop();
        }else {
            mTimeCountWatcher.onProgress(current_time, TIME_DELAY, total_time);
            mProgressHandler.postDelayed(mProgressRunnable, TIME_DELAY);
        }
    }

    private void onPause() {
        mTimeCountWatcher.onPause(current_time);
    }

    private void onStop() {
        mTimeCountWatcher.onStop(current_time);
    }

    private static class WeakHandler extends Handler {

        private final WeakReference<TimeCounter> mRef;

        WeakHandler(TimeCounter timeCounter){
            super(AppTool.getInstance().getApplication().getMainLooper());
            mRef = new WeakReference<>(timeCounter);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            TimeCounter timeCounter = mRef.get();
            switch (msg.what){
                case DOING:
                    timeCounter.onProgress();
                    break;
                case PAUSE:
                    timeCounter.onPause();
                    break;
                case STOP:
                    timeCounter.onStop();
                    break;
            }
        }
    }

    public long getCurrentTime(){
        return current_time;
    }

    @Override
    public void start(){
        if(mProgressHandler == null){
            mProgressHandler = new Handler();
            mWeakHandler = new WeakHandler(this);
            mProgressRunnable = new Runnable() {
                @Override
                public void run() {
                    mWeakHandler.sendEmptyMessage(DOING);
                }
            };
        }else {
            mProgressHandler.removeCallbacksAndMessages(null);
            mWeakHandler.removeCallbacksAndMessages(null);
        }
        mProgressHandler.postDelayed(mProgressRunnable, TIME_DELAY);
    }

    @Override
    public void pause() {
        if(mProgressHandler != null){
            mProgressHandler.removeCallbacksAndMessages(null);
            mWeakHandler.removeCallbacksAndMessages(null);
            onPause();
        }else {
            DLog.e("make sure the Timer exist!");
        }
    }

    @Override
    public void stop() {
        if(mProgressHandler != null){
            mProgressHandler.removeCallbacksAndMessages(null);
            mWeakHandler.removeCallbacksAndMessages(null);
            mProgressRunnable = null;
            mProgressHandler = null;
            mWeakHandler = null;

            onStop();
        }else {
            DLog.e("make sure the Timer exist!");
        }
    }
}
