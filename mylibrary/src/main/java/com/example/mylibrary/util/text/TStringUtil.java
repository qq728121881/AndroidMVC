package com.example.mylibrary.util.text;

import android.text.TextUtils;


import com.example.mylibrary.util.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统一的字符串处理方法
 */
public final class TStringUtil {

    private static final String REGEX_1 = ",";

    /**
     * List<String> 转化成 String
     */
    public static String formatStr(List<String> list) {

        if (ListUtil.isEmpty(list)) return "";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i));
            builder.append(REGEX_1);
        }
        builder.append(list.get(list.size() - 1));
        return builder.toString();
    }

    /**
     * String 转化成 List<String>
     */
    public static List<String> unFormatStr(String str) {

        if (TextUtils.isEmpty(str)) return new ArrayList<>();

        if (!str.contains(REGEX_1)) {
            return new ArrayList<String>() {
                {
                    add(str);
                }
            };
        }

        return new ArrayList<>(Arrays.asList(str.split(REGEX_1)));
    }

    /**
     * 去除字符串中的空字符
     * \t \r \n 都认为是空字符
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
