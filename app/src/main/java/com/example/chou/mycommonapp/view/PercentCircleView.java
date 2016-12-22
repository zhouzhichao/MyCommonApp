package com.example.chou.mycommonapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Chou on 2016/12/16.
 */

public class PercentCircleView extends View {
    private Paint mPaintCircle;//用于画空心圆
    private Paint mPaintFilledCircle;
    private Paint mPaintTextValue;
    private float mProgress;
    private float circleFilledRadius = dip2px(getContext(), 5);//外圆半径
    private final int mCircleLineStrokeWidth = dip2px(getContext(), 4);
    public void setPaints(int circleColor,int textDateColor) {
        mPaintCircle.setColor(circleColor);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(5);
        mPaintCircle.setAntiAlias(true);
        mPaintTextValue.setColor(textDateColor);
        mPaintTextValue.setTextSize(dip2px(getContext(), 12));
        mPaintTextValue.setTextAlign(Paint.Align.CENTER);
        mPaintTextValue.setAntiAlias(true);
        mPaintFilledCircle.setColor(textDateColor);
        mPaintFilledCircle.setStyle(Paint.Style.FILL);
        mPaintFilledCircle.setAntiAlias(true);
        invalidate();//重绘
    }
    //用于设定传入的总数据
    public void setData(float mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }
    public PercentCircleView(Context context) {
        super(context);
    }

    public PercentCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintCircle = new Paint();
        mPaintTextValue = new Paint();
        mPaintFilledCircle=new Paint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        RectF mRectF = new RectF();
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y
        // 绘制圆圈，进度条背景
        mPaintCircle.setColor(Color.argb(255,105, 210, 249));
        canvas.drawArc(mRectF, 0, 360, false, mPaintCircle);
        mPaintCircle.setColor(Color.rgb(0xff, 0x82, 0x82));
        canvas.drawArc(mRectF, 0, ((float) mProgress / 100) * 360, false, mPaintCircle);
        mPaintCircle.getTextScaleX();
        String text=mProgress+"%";
        canvas.drawText(text,150,150,mPaintTextValue);
    }
    public int dip2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
