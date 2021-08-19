package com.example.mylibrary.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * @作者 邸昌顺
 * @时间 2019/1/7 17:51
 * @描述
 */
public class BitmapUtil {

    public static Bitmap changeBitmapSize(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //计算压缩的比率
        float scaleWidth=((float)newWidth)/width;
        float scaleHeight=((float)newHeight)/height;

        return changeBitmapSize(bitmap, scaleWidth, scaleHeight);
    }

    public static Bitmap changeBitmapSize(Bitmap bitmap, float scaleX, float scaleY) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        //获取新的bitmap
        bitmap = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);

        return bitmap;
    }

}
