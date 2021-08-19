package com.example.mylibrary.util;


import com.example.mylibrary.tool.log.DLog;

import java.util.HashMap;

public final class ChineseNumberUtil {

    public static void logTest(int number) {
        for (int i = 0; i <= number; i++) {
            DLog.d(recurToGetChineseNumberChar(i + 1));
        }
    }

    public static String recurToGetChineseNumberChar(int number) {
        String[] numberChar = new String[]{
                "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
        };
        String[] units = new String[]{
                "", "十", "百", "千", "万",
        };

        //按照从个位开始的顺序 将数字转化成汉字
        String numString = "" + number;
        int length = numString.length();
        String tempNumberString = "";
        for (int i = length - 1; i >= 0; i--) {
            tempNumberString = numberChar[Integer.parseInt("" + numString.charAt(i))] + units[length - 1 - i] + tempNumberString;
        }

        //处理特殊格式
        if (tempNumberString.startsWith("一十")) {
            tempNumberString = tempNumberString.replaceFirst("一", "");
        }
        tempNumberString = tempNumberString.replace("零百", "零");
        tempNumberString = tempNumberString.replace("零十", "零");
        while (tempNumberString.contains("零零")) {//处理中间是零零的情况
            tempNumberString = tempNumberString.replace("零零", "零");
        }
        while (tempNumberString.endsWith("零")) {
            tempNumberString = tempNumberString.substring(0, tempNumberString.length() - 1);
        }

        return tempNumberString;
    }

    public static int getIntValueByChineseNumber(String chineseNumber) {
        DLog.e("chineseNumber=" + chineseNumber);
        String[] chineseNumbers = new String[]{
                "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二",
        };
        Integer[] intValues = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };
        HashMap<String, Integer> filterMap = new HashMap<>();
        for (int i = 0; i < chineseNumbers.length; i++) {
            filterMap.put(chineseNumbers[i], intValues[i]);
        }

        if(filterMap.containsKey(chineseNumber)){
            return filterMap.get(chineseNumber);
        }else {
            return 13;//默认返回13，，，呵呵呵
        }
    }
}
