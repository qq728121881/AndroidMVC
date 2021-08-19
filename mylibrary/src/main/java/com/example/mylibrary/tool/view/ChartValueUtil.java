package com.example.mylibrary.tool.view;

public final class ChartValueUtil {

    public static final int TEN_THOUSAND = 10000;
    public static final int THOUSAND = 1000;
    public static final int HUNDRED = 100;
    public static final int TEN = 10;
    public static final int UNIT = 1;

    //根据后台返回数据，设置坐标轴数值
    public static int[] getLimitNumValue(int minValue, int maxValue, int labCount){

        int scaleValue = 10;
        int totalScale = maxValue - minValue;
        int[] scaleArr = new int[]{
                TEN_THOUSAND, THOUSAND, HUNDRED, TEN, UNIT
        };
        for(int i=0;i<scaleArr.length - 1;i++){
            if(totalScale > scaleArr[i]){
                scaleValue = scaleArr[i+1];
                break;
            }
        }
        int minScaleValue = (int) Math.floor(minValue/scaleValue) * scaleValue;
        int maxScaleValue = (int) Math.ceil(maxValue/scaleValue) * scaleValue;

        scaleValue = (maxScaleValue - minScaleValue) / scaleValue;

        maxScaleValue += scaleValue;
        minScaleValue -= scaleValue;
        if(minScaleValue < 0){
            minScaleValue = 0;
        }

        return new int[]{minScaleValue, maxScaleValue};
    }
}
