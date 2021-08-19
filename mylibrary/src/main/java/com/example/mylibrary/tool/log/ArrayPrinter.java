package com.example.mylibrary.tool.log;

import android.util.Log;

public class ArrayPrinter {

    public ArrayPrinter(){}

    public synchronized void print(String tag, byte[] bytes) {

        if (DLog.logFilter()){
            return;
        }

        if (bytes == null) {
            Log.d(tag, "Array bytes == null");
            return;
        }

        final String splitString = ", ";
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i=0; i< bytes.length; i++) {
            byte b = bytes[i];
            stringBuilder.append(b);
            if (i != bytes.length - 1) {
                stringBuilder.append(splitString);
            }
        }
        stringBuilder.append("]");

        Log.d(tag, stringBuilder.toString());
    }
}
