package com.example.mylibrary.tool.verify.filter;

/**
 * @作者 邸昌顺
 * @时间 2019/1/8 15:31
 * @描述
 */
public class FilterManger {

    private volatile static FilterManger sFilterManger = null;
    private FilterManger(){}
    public static FilterManger instance(){
        if(sFilterManger == null){
            synchronized (FilterManger.class){
                if(sFilterManger == null){
                    sFilterManger = new FilterManger();
                }
            }
        }
        return sFilterManger;
    }

    //多条件判断，添加过滤器，会按照顺序进行判断，索引越小，越先判断
    public boolean filter(Filter[] filters){
        for (Filter filter : filters){
            if(!filter.filter()){
                continue;//不过滤, 则继续判断
            }
            return true;//满足过滤条件
        }
        return false;//默认不过滤
    }

    //单条件判断
    public boolean filter(Filter filter){
        return filter.filter();
    }

}
