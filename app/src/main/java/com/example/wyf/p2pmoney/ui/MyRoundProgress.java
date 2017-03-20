package com.example.wyf.p2pmoney.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.wyf.p2pmoney.R;

import static com.example.wyf.p2pmoney.global.MyApplication.context;

/**
 * Created by WYF on 2017/3/20.
 */

public class MyRoundProgress extends View {

    private Paint paint = new Paint();
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int progress = 90;
    private int max = 100;

    public MyRoundProgress(Context context) {
        this(context, null);
    }

    public MyRoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRoundProgress);
        //圆环的颜色
        roundColor = mTypedArray.getColor(R.styleable.MyRoundProgress_myRoundColor, Color.BLUE);
        //圆环进度的颜色
        roundProgressColor = mTypedArray.getColor(R.styleable.MyRoundProgress_myRoundProgressColor, Color.GREEN);
        //中间进度百分比文字字符串的颜色
        textColor = mTypedArray.getColor(R.styleable.MyRoundProgress_myTextColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textSize = mTypedArray.getDimension(R.styleable.MyRoundProgress_myTextSize, 15);
        //圆环的宽度
        roundWidth = mTypedArray.getDimension(R.styleable.MyRoundProgress_myRoundWidth, 5);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);   //抗锯齿
        int center = getWidth()/2;  //圆心位置
        int radius = (int)(center - roundWidth/2);  //半径
        canvas.drawCircle(center, center, radius, paint);

        float textWidth =paint.measureText(progress +"%");
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        //文字left位置，top位置
        canvas.drawText(progress +"%", center-textWidth/2, center+textSize/2, paint);

        RectF rectF = new RectF(center-radius, center-radius, center+radius, center+radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        //recf矩形位置   useCenter 包圆心
        canvas.drawArc(rectF, 0, 360 * progress / max, false, paint);
    }

    public void setProgerss(int progress){
        this.progress = progress;
        if (progress > 100) {
            this.progress = 100;
        }
        postInvalidate();
    }
}
