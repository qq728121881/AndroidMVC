package com.example.mylibrary.util;

public class QuestionTypeTitleUtil {

    public static String formatQuTitle(String flagStr, String quTitle){
        return String.format("【%1$s】%2$s", flagStr, quTitle);
    }

    public static String formatQuTitle(int icChoices, String quTitle){
        return String.format("【%1$s】%2$s", icChoices == 0 ? "单选题" : "多选题", quTitle);
    }
}
