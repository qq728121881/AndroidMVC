package com.example.mylibrary.util;

import java.util.List;

public class ListUtil {

    public static <T> boolean isEmpty(List<T> list){
        if(list == null){//需要验证是否为空
            return true;
        }
        return list.isEmpty();
    }

}
