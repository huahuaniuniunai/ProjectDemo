package com.example.projectdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;

/**
 * 自定义View标准写法
 *
 * 如果自定义view继承ViewGroup是不会执行onDraw方法的
 * 解决方案：1、重写dispatchDraw代替onDraw方法（内容不变）
 *         2、创建画笔时默认给一个透明背景
 *         3、创建画笔时setWillNotDraw(false);重新赋值
 */

@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    private Paint mTextPaint;
    private String mText;
    private int mTextColor = Color.BLACK;
    private int mTextSize = 15;

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        // 获取文本
        mText = array.getString(R.styleable.CustomTextView_customText);
        // 获取文字颜色
        mTextColor = array.getColor(R.styleable.CustomTextView_customTextColor, mTextColor);
        // 获取文字大小   15sp
        mTextSize = array.getDimensionPixelSize(R.styleable.CustomTextView_customTextSize, mTextSize);
        // 回收
        array.recycle();

        // 初始化画笔
        init();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);// 设置为填充模式
        mTextPaint.setStrokeWidth(10f);// 设置画笔宽度为10px
        mTextPaint.setColor(mTextColor);// 设置画笔颜色
        mTextPaint.setTextSize(mTextSize);// 设置画笔尺寸
        mTextPaint.setAntiAlias(true);// 抗锯齿

        // 默认给一个透明背景（解决自定义view继承viewgroup不显示问题）
//        setBackgroundColor(Color.TRANSPARENT);
        // 重新赋值
//        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // heightMeasureSpec是32位的值
        // 获取宽高的模式(前2位)     3种测量模式：AT_MOST、EXACTLY、UNSPEC
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 获取宽高的值(后30位)
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 解决ScrollView嵌套ListView显示不全问题
        // 前2位是MeasureSpec.AT_MOST，后30位是Integer.MAX_VALUE
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        // 计算控件宽高
        if (widthMode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            mTextPaint.getTextBounds(mText, 0, mText.length(), rect);
            // getPaddingLeft()和getPaddingRight()在XML设置padding才会生效
            widthSize = rect.width() + getPaddingLeft() + getPaddingRight();
            // getPaddingTop()和getPaddingBottom()在XML设置padding才会生效
            heightSize = rect.height() + getPaddingTop() + getPaddingBottom();
        }
        // 计算控件高度
//        if (widthMode == MeasureSpec.AT_MOST) {
//            Rect rect = new Rect();
//            mTextPaint.getTextBounds(mText, 0, mText.length(), rect);
//            // getPaddingTop()和getPaddingBottom()在XML设置padding才会生效
//            heightSize = rect.height() + getPaddingTop() + getPaddingBottom();
//        }
        // 设置控件的宽高
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画文本
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        // 中线到基线的距离
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 -fontMetricsInt.bottom;
        // 基线的y值
        int baseLine = getHeight()/2 + dy;
        int x = getPaddingLeft();
        canvas.drawText(mText, x, baseLine, mTextPaint);

        // 画圆
//        canvas.drawCircle();
        // 画bitmap
//        canvas.drawBitmap();
        // 画弧
//        canvas.drawArc();

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("info", "手势按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("info", "手势移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("info", "手势抬起");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("info", "拦截取消");
                break;
        }
        return true;
    }

}
