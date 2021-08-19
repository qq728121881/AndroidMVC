package com.example.mylibrary.help;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.mylibrary.frame.util.absviewlistener.TextWatcherImpl;


public final class BoundInputValueHelper {

    private EditText etInputView;
    private int minValue;
    private int maxValue;
    private ValueSetCallback valueSetCallback;

    private boolean isEtHasFocus;

    public BoundInputValueHelper(EditText etInputView, int minValue, int maxValue,  ValueSetCallback valueSetCallback) {
        this.etInputView = etInputView;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.valueSetCallback = valueSetCallback;

        isEtHasFocus = false;
    }

    /**
     * 如果超出设定的取值范围
     * 则返回true
     * */
    public boolean checkValueOutBoundary(int value){
        return value > maxValue || value < minValue;
    }

    public void releaseChecker(){
        etInputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etInputView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        isEtHasFocus = false;
    }

    public void addChecker() {

        etInputView.addTextChangedListener(new TextWatcherImpl() {

            String agoString;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                super.beforeTextChanged(s, start, count, after);
                agoString = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s != null && !TextUtils.isEmpty(s.toString())) {
                    if (!s.toString().equals(agoString)) {
                        int value = Integer.parseInt(s.toString().trim());
                        if(value > maxValue ){
                            valueSetCallback.valueSet(maxValue, ValueSetCallback.TYPE_OUT_BOUNDARY);
                        }else if(value < minValue){
                            valueSetCallback.valueSet(minValue, ValueSetCallback.TYPE_OUT_BOUNDARY);
                        }else {
                            valueSetCallback.valueSet(value, ValueSetCallback.TYPE_IN_BOUNDARY);
                        }
                    }
                } else {
                    valueSetCallback.valueSet(minValue, ValueSetCallback.TYPE_OUT_BOUNDARY);
                }
            }
        });

        etInputView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //焦点变化只为了回调出 数值！！！
                if (isEtHasFocus && !hasFocus) {
                    if (TextUtils.isEmpty(etInputView.getText().toString().trim())) {
                        valueSetCallback.valueSet(minValue, ValueSetCallback.TYPE_CLEAR_FOCUS_OUT_BOUNDARY);
                    } else {
                        int value = Integer.parseInt(etInputView.getText().toString().trim());
                        if(value > maxValue ){//超过最大值
                            valueSetCallback.valueSet(maxValue, ValueSetCallback.TYPE_CLEAR_FOCUS_OUT_BOUNDARY);
                        }else if(value < minValue){//小于最小值
                            valueSetCallback.valueSet(minValue, ValueSetCallback.TYPE_CLEAR_FOCUS_OUT_BOUNDARY);
                        }else {
                            valueSetCallback.valueSet(value, ValueSetCallback.TYPE_CLEAR_FOCUS);
                        }
                    }
                }
                isEtHasFocus = hasFocus;
            }
        });


    }

    public void clearFocus(){
        etInputView.clearFocus();
    }

    /**
     * 输入框内容改变或者焦点变化，会回调该接口
     * */
    public interface ValueSetCallback{

        int TYPE_CLEAR_FOCUS = 0;
        int TYPE_OUT_BOUNDARY = 1;
        int TYPE_IN_BOUNDARY = 2;
        int TYPE_CLEAR_FOCUS_OUT_BOUNDARY = 3;

        /**
         * 回调要用到的数值
         *
         * @param value 输入内容
         * @param isOutBoundary 是否超出了可输入范围， 类型
         * */
        void valueSet(int value, int isOutBoundary);
    }
}
