package com.example.mylibrary.util.input;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * @作者 邸昌顺
 * @时间 2018/12/25 20:15
 * @描述
 */
public class ChineseFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//        for (int i = start; i < end; i++) {
//            Log.e("test", "" + source.charAt(i));
//            if ((!isChinese(source.charAt(i)))
//                    && (!Character.isLetterOrDigit(source.charAt(i)))
//                    && (!Character.isWhitespace(source.charAt(i)))
//                    && ('—' != (source.charAt(i))
//                    )) { //&& ('·' != (source.charAt(i)))
//                return "";
//            }
//        }
        return null;
    }


    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}
