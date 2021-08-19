package com.example.mylibrary.util;

public class FormatUtil {

    public static String formatLogString(String logMessage){
        if(logMessage == null){
            return "the log message is null";
        }
        if("".equals(logMessage)){
            return "the log message is empty";
        }
        return "<--- " + logMessage + "--->";
    }
}
