package com.example.mylibrary.util.input;

import android.text.InputFilter;
import android.text.Spanned;


import com.example.mylibrary.widget.UICToast;

import java.util.Locale;

/**
 * @作者 邸昌顺
 * @时间 2018/11/28 12:27
 * @描述
 */
public class MaxTextLengthFilter implements InputFilter {

    private int mMaxLength;
    private String mTips;

    public MaxTextLengthFilter(int max) {
        mMaxLength = max;
        mTips = String.format(Locale.getDefault(), "最多输入%d字符", max) ;
    }

    public MaxTextLengthFilter(int max, String tips) {
        mMaxLength = max;
        mTips =tips;
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if (keep < (end - start)) {
            UICToast.instance().showNormalToast(mTips);
        }
        if (keep <= 0) {
            return "";
        } else if (keep >= end - start) {
            return null;
        } else {
            return source.subSequence(start, start + keep);
        }
    }

}
