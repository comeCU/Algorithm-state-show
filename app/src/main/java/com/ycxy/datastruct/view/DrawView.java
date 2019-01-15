package com.ycxy.datastruct.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ycxy.datastruct.state.State;
import com.ycxy.datastruct.state.StateManager;

//该类已稳定，不需要改代码。
public class DrawView extends View {
    final String TAG="DrawView";
    private State mState=null;//引用外部的状态对象

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //外部对象想画图了，就调用该方法，传入要呈现的数据state
    public void draw(State state){
        mState=state;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mState != null){
            mState.onDraw(canvas);
        }
    }
}


