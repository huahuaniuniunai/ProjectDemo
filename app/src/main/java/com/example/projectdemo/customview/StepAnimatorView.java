package com.example.projectdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;

public class StepAnimatorView extends View {
    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 20;// px像素
    private int mStepTextSize;
    private int mStepTextColor;
    private Paint mOuterPaint, mInnerPaint, mTextPaint;
    private int mStepMax;// 总共的步数
    private int mCurrentStep;// 当前的步数

    public StepAnimatorView(Context context) {
        this(context, null);
    }

    public StepAnimatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 1、在attrs.xml中设置自定义属性
        // 2、在布局中使用自定义view
        // 3、设置自定义属性变量和初始化默认值
        // 4、获取自定义属性并赋默认值
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepAnimatorView);
        mOuterColor = array.getColor(R.styleable.StepAnimatorView_outerColor, mOuterColor);// 赋默认值
        mInnerColor = array.getColor(R.styleable.StepAnimatorView_innerColor, mInnerColor);
        mBorderWidth = (int) array.getDimension(R.styleable.StepAnimatorView_borderWidth, mBorderWidth);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.StepAnimatorView_stepTextSize, mStepTextSize);
        mStepTextColor = array.getColor(R.styleable.StepAnimatorView_stepTextColor, mStepTextColor);
        array.recycle();// 回收
        // 5、初始化画笔
        init();
    }

    private void init() {
        // 新建内圆弧画笔
        mInnerPaint = new Paint();
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);// 端部圆弧
        mInnerPaint.setStyle(Paint.Style.STROKE);// 空心
        mInnerPaint.setAntiAlias(true);

        // 新建外圆弧画笔
        mOuterPaint = new Paint();
        mOuterPaint.setColor(mOuterColor);
        mOuterPaint.setStrokeWidth(mBorderWidth);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);// 端部圆弧
        mOuterPaint.setStyle(Paint.Style.STROKE);// 空心
        mOuterPaint.setAntiAlias(true);

        // 新建文本画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(mOuterColor);
        mTextPaint.setTextSize(mStepTextSize);
        mTextPaint.setAntiAlias(true);
    }

    // 6、onMeasure测量xml中控件设置的宽高及模式，此处设置控件为一个正方形
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 设置宽高不一致时取最小值，这里确保是一个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    // 7、onDraw开始画

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画内圆弧
        RectF rectF = new RectF(mBorderWidth, mBorderWidth, getWidth()-mBorderWidth, getHeight()-mBorderWidth);// 确定圆弧区域
        canvas.drawArc(rectF, 135, 270, false, mInnerPaint);

        // 画外圆弧(动态)
        if (mStepMax == 0) return;
        float angle = (float) mCurrentStep / mStepMax;
        canvas.drawArc(rectF, 135, angle * 270, false, mOuterPaint);

        // 画文字
        // 先确定基线
        Paint.FontMetricsInt metricsInt = mTextPaint.getFontMetricsInt();
        int dy = (metricsInt.bottom - metricsInt.top)/2 - metricsInt.bottom;
        int baseline = getHeight()/2 + dy;

        String stepText = String.valueOf(mCurrentStep);
        Rect rect = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), rect);
        canvas.drawText(stepText, (getWidth()-rect.width())/2, baseline, mTextPaint);

    }

    // 8、其他，此处为动画效果
    // 设置最大值
    public synchronized void setStepMax(int stepMax) {
        this.mStepMax = stepMax;
    }
    // 设置当前值，并重绘
    public synchronized void setCurrentStep(int currentStep) {
        this.mCurrentStep = currentStep;
        invalidate();// 不断调用onDraw重绘
    }
}
