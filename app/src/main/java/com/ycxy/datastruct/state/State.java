package com.ycxy.datastruct.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * 状态类的基类，相对稳定，不需要改。
 */
public class State {
    int mId;
    String mTitle;
    int mCodeLine;//代码的行号
    Paint mPaint, mPaintBlue, mPaintRed;//三种颜色的画笔工具，子类可以直接拿着用。
    ArrayList<String> mVars = new ArrayList<>();//以字符串表示的变量集合

    public State() {
        initPaints();
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setCodeLine(int codeLine) {
        mCodeLine = codeLine;
    }

    public int getCodeLine() {
        return mCodeLine;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void addVariable(String name, Object value) {
        mVars.add(name + " : " + value);
    }

    public ArrayList<String> getVariables() {
        return mVars;
    }

    public void onDraw(Canvas canvas){
        //Do nothing.
    }

    //初始化一些画图工具
    void initPaints() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(20);
        mPaint.setTextAlign(Paint.Align.LEFT);

        mPaintBlue = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaintBlue.setTextSize(20);
        mPaint.setTextAlign(Paint.Align.LEFT);
//        mPaintBlue.setColor(Color.BLUE);
        mPaintBlue.setColor(Color.BLUE);

        mPaintRed = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaintRed.setTextSize(20);
        mPaintRed.setColor(Color.RED);
    }

    //在矩形rect中画字符串text,里面的代码不要看，看怎么调用就行。
    void drawText(Canvas canvas, String text, Rect rect, Paint paint){
        Rect textRect=new Rect();
        mPaint.getTextBounds(text,0,text.length(),textRect);
        int x=rect.left+(rect.width()-textRect.width())/2;
        int y=rect.bottom-(rect.height()-textRect.height())/2;
        canvas.drawText(text,x,y,paint);
    }
}