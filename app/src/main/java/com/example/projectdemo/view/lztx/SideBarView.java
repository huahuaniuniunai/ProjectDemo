package com.example.projectdemo.view.lztx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;

class SideBarView extends View {
    private Paint mPaint;
    private int mWidth, mHeight, textSize;
    private String[] str = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z", "#"};

//    public SideBarView(Context context) {
//        this(context, null);
//    }

    public SideBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                14, getResources().getDisplayMetrics());
        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        mPaint.setAntiAlias(true);// 抗锯齿
        mPaint.setDither(true);// 防抖动
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();
        for (int i = 0; i < str.length; i++) {
            /**
             * 获取字符串宽度：mPaint.measureText()和mPaint.getTextBound()
             * getTextBounds()得到的宽度总是比 measureText() 得到的宽度要小一点，是因为两种方法测量的方式不一样。
             */
            float x = (mWidth - mPaint.measureText(str[i])) / 2;
            float y = (mHeight / str.length) * (i + 1);
            mPaint.setColor(getResources().getColor(R.color.blue));
            canvas.drawText(str[i], x, y, mPaint);
        }
    }
}
