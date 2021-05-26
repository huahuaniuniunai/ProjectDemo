package com.example.projectdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;

/**
 * 自定义View标准写法
 */

@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    private Paint mTextPaint;
    private String mText = "";
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
        mText = array.getString(R.styleable.CustomTextView_text);
        mTextColor = array.getColor(R.styleable.CustomTextView_textColor, mTextColor);
        // 15sp
        mTextSize = array.getDimensionPixelSize(R.styleable.CustomTextView_textSize, mTextSize);
        // 初始化画笔
        init();
        // 回收
        array.recycle();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);// 设置为填充模式
        mTextPaint.setStrokeWidth(10f);// 设置画笔宽度为10px
        mTextPaint.setColor(mTextColor);// 设置画笔颜色
        mTextPaint.setTextSize(mTextSize);// 设置画笔尺寸
        mTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // heightMeasureSpec是32位的值
        // 获取宽高的模式(前2位)
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 获取宽高的值(后30位)
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 解决ScrollView嵌套ListView显示不全问题
        // 前2位是MeasureSpec.AT_MOST，后30位是Integer.MAX_VALUE
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

}
