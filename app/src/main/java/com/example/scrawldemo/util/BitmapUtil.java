package com.example.scrawldemo.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Project:AndroidDemo
 * Author:dyping
 * Date:2017/4/11 10:40
 */

public class BitmapUtil {

    /**
     * 根据容器把原始图片改变大小。从而适应容器。
     *
     * @param bitmap
     * @param groupView
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, View groupView) {

        int bitmapHeight = bitmap.getHeight();
        int bitmapWidth = bitmap.getWidth();
        int groupHeight = groupView.getHeight();
        int groupWidth = groupView.getWidth();

        float scale = bitmapHeight > bitmapWidth
                ? groupHeight / (bitmapHeight * 1f)
                : groupWidth / (bitmapWidth * 1f);

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);
    }

    public static void SaveScreenShot(Activity activity,String path) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        FileOutputStream outputStream = null;
        try {
             outputStream = new FileOutputStream(path);
             bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            view.setDrawingCacheEnabled(false);
        }
    }
}
