package com.example.mylibrary.tool.verify.filter;


import com.example.mylibrary.tool.NetworkTool;
import com.example.mylibrary.widget.UICToast;

public class NetFilter extends Filter{

    @Override
    public boolean filter(){
        boolean isMatch = NetworkTool.isNetworkAvaliable();
        if(!isMatch){
            UICToast.instance().showNormalToast("网络异常，请检查网络");
        }
        return !isMatch;
    }

}
