package com.ycxy.datastruct.state;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * @author dong
 */
public class SortState extends State {
    String mtitle;
    int[] mArray;
    private int min, point;
    int step=0;

    public SortState(String title, int[] arr, int i, int j, int step){
        this.mTitle = title;
        this.mArray = arr;
        this.min = i;
        this.point = j;
        this.step = step;
    }

    //////////////////////////////////////////////////////////////
    int mOrgX =20, mOrgY =20;       //链表节点在视图上的起始偏移位置
    int mNodeCx=50,mNodeCy=40;      //每个节点的尺寸

    public void onDraw(Canvas canvas){
        int x=mOrgX,y=mOrgY;
        Rect rectTitle=new Rect(x,y,canvas.getWidth()-x,y+40);
        drawText(canvas,mId+" "+mTitle,rectTitle,mPaint);
        y+=40;
        for(int i = 0; i< mArray.length; i++){
            Rect rect=new Rect(x+mNodeCx*i,
                    y - toHeight(step,i),
                    x+mNodeCx*(i+1),
                    y+mNodeCy - toHeight(step,i));
            canvas.drawRect(rect,mPaint);//画节点矩形

            if ((step ==1 || step ==2) && (min == i || point == i)) {    // 前两个状态
                drawText(canvas, "" + mArray[i], rect, mPaintRed);//画节点中的数据
            } else {
                drawText(canvas, "" + mArray[i], rect, mPaint);//画节点中的数据
            }

            rect.offset(0,mNodeCx);
            drawText(canvas,""+i,rect,mPaint);//画节点下标
        }
    }

    // 交换时矩形上升
    public int toHeight(int step, int i) {
        if((step ==1 || step ==2) && (min == i || point == i)) {
            return 20;
        }
        return 0;
    }

}

