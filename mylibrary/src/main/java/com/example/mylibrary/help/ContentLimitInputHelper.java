package com.example.mylibrary.help;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ContentLimitInputHelper {

    private EditText etInput;
    private String limitContent;
    private int maxInputNumber;

    public ContentLimitInputHelper(EditText etInput, String limitContent, int maxInputNumber) {
        this.etInput = etInput;
        this.limitContent = limitContent;
        this.maxInputNumber = maxInputNumber;
    }

    public void addChecker() {
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s != null) {
                    if (s.length() > maxInputNumber) {//字符长度限制
                        etInput.setText(s.subSequence(0, maxInputNumber));
                        etInput.setSelection(s.length());
                        return;
                    }

                    try {
                        String temp = s.toString();
                        String tem = temp.substring(temp.length() - 1);
                        char[] temC = tem.toCharArray();
                        int mid = temC[0];
                        boolean isFilter = true;
                        for (char filter : limitContent.toCharArray()) {
                            if (filter == mid) {
                                isFilter = false;
                                break;
                            }
                        }
                        if (isFilter) {
                            s.delete(temp.length() - 1, temp.length());
                            etInput.setText(s.toString());
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    public void releaseChecker(){
        etInput.addTextChangedListener(new TextWatcher() {
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
    }
}
