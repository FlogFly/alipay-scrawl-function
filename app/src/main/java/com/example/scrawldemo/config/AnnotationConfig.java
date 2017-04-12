package com.example.scrawldemo.config;

import android.support.annotation.IntDef;

/**
 * Created by willy on 17/2/15.
 */

public class AnnotationConfig {

    @IntDef({PaintType.Paint_Red, PaintType.Paint_Orange, PaintType.Paint_Yellow, PaintType.Paint_Green, PaintType.Paint_Blue, PaintType.Paint_Purple, PaintType.Paint_Eraser})
    public @interface PaintType{
        public static final int Paint_Red = 1;
        public static final int Paint_Orange = 2;
        public static final int Paint_Yellow = 3;
        public static final int Paint_Green = 4;
        public static final int Paint_Blue = 5;
        public static final int Paint_Purple = 6;
        public static final int Paint_Eraser = 7;
    }
}




