package com.example.mylibrary.tool.verify.filter;

import android.text.InputFilter;
import android.text.Spanned;


import com.example.mylibrary.tool.log.DLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        Pattern emoji = Pattern.compile(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\ud83e\udc00-\ud83e\udfff]|[\u2600-\u27ff]|[\ud83e\udd10-\ud83e\udd17]|[\ud83e\udd81-\ud83e\udd8a]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(charSequence);
        DLog.e("[EmojiFilter] charSequence=" + charSequence.toString() + ", " + stringToUnicode(charSequence.toString()));
        if (emojiMatcher.find()) {
            return "";
        }
        return null;
    }

    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            //"\\u只是代号，请根据具体所需添加相应的符号"
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
}
