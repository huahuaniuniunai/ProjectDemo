package com.example.projectdemo.lztx.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;

/**
 * 自定义View标准写法
 */
public class SideBarView extends View {
    private Paint mPaint;
    private int mWidth, mHeight, textSize;
    private int index;
    private int choose = -1;
    private boolean mFlag;// 默认未选中
    private LetterTouchListener mTouchListener;
    private String[] str = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z", "#"};

    // 第一个构造函数：在代码中直接new一个调用，并通过this关键字调用第二个构造函数
    public SideBarView(Context context) {
        this(context, null);
    }

    // 第二个构造函数：在xml布局文件中调用，并通过this关键字调用第三个关键字
    public SideBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 第三个构造函数：在这里进行初始化画布、画笔
    public SideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                14, getResources().getDisplayMetrics());// 画布
        mPaint = new Paint();// 画笔
        mPaint.setTextSize(textSize);
        mPaint.setAntiAlias(true);// 抗锯齿,等同于Paint.ANTI_ALIAS_FLAG
        mPaint.setDither(true);// 防抖动
//        mPaint.setColor(Color.BLACK);       //设置画笔颜色
//        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
//        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
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
            if (choose == i) {
                mPaint.setColor(getResources().getColor(R.color.red));
            } else {
                mPaint.setColor(getResources().getColor(R.color.blue));
            }
            canvas.drawText(str[i], x, y, mPaint);
        }
        invalidate();//触摸滑动刷新,invalidate是在UI线程中使用刷新view，postInvalidate是在非UI线程中使用
    }

    /**
     * 触摸滑动事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前手指触摸的Y位置
        float y = event.getY();
        // 根据当前触摸的位置计算出，当前字母的位置
        index = (int) (y / mHeight * str.length);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:// 按下View（所有事件的开始）
                mTouchListener.setLetterVisibility(View.VISIBLE);
                mTouchListener.setLetter(str[index]);
                choose = index;
                mFlag = true;
                break;
            case MotionEvent.ACTION_MOVE:// 滑动View
                if (index > -1 && index < str.length) {
                    mTouchListener.setLetter(str[index]);
                    choose = index;
                    mFlag = true;
                }
                break;
            case MotionEvent.ACTION_UP:// 抬起View（与DOWN对应）
                mTouchListener.setLetterVisibility(View.GONE);
                choose = -1;
                mFlag = false;
                break;
                // MotionEvent.ACTION_CANCEL：结束事件
        }
        return true;
    }

    public void setLetterTouchListener(LetterTouchListener listener) {
        mTouchListener = listener;
    }

    public interface LetterTouchListener {
        void setLetterVisibility(int visibility);
        void setLetter(String letter);
    }
}
