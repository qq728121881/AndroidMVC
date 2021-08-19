package com.example.mylibrary.tool.timecount;

public interface TimeCountWatcher {

    void onProgress(long currentTime, long marginTime, long totalTime);

    void onPause(long currentTime);

    void onStop(long currentTime);

    void onEnd();
}
