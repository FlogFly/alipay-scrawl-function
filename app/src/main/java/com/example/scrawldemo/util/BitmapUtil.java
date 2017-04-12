package com.example.scrawldemo.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;

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
     *
     * 图片过大则根据容器把原始图片改变大小。从而适应容器。
     * 否则改变画板大小适应图片
     *
     * @param bitmap
     * @param boardView
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, View boardView) {

        int bitmapHeight = bitmap.getHeight();
        int bitmapWidth = bitmap.getWidth();
        int boardHeight = boardView.getHeight();
        int boardWidth = boardView.getWidth();

        float scale = 1f;
        if(bitmapHeight > boardHeight || bitmapWidth > boardWidth){
            scale = bitmapHeight > bitmapWidth
                    ? boardHeight / (bitmapHeight * 1f)
                    : boardWidth / (bitmapWidth * 1f);
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);

        ViewGroup.LayoutParams params = boardView.getLayoutParams();
        params.height = resizeBitmap.getHeight();
        params.width = resizeBitmap.getWidth();
        boardView .setLayoutParams(params);

        return resizeBitmap;
    }


    /**
     * 截屏并将图片保存到相应路径下
     *
     * @param activity 当前需要截屏的activity
     * @param path     图片保存路径
     */
    public static void SaveScreenShot(Activity activity, String path) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
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
