package com.ycxy.datastruct.state;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.ycxy.datastruct.algorithm.LTNode;

import java.util.ArrayList;

/**
 * 工作重心，以后主要修改的代码在此类中。
 */
public class LinearTableState extends State{
    private LTNode[] mNodes;
    private int mCurNode;
    private int mCodeLine;//代码的行号
    private ArrayList<String> mVars=new ArrayList<>();//以字符串表示的变量集合

    public LinearTableState(LTNode[] nodes, boolean cloneNodes){
        if(cloneNodes){
            mNodes =new LTNode[nodes.length];
            for(int i=0; i<nodes.length; i++){
                if(nodes[i] != null) {
                    mNodes[i] = nodes[i].clone();
                }
            }
        }else{
            mNodes =nodes;
        }
    }


    public void setCurNode(int curNode){
        mCurNode=curNode;
    }

    ////////////////////下面代码与画图有关
    int mOrgX =20, mOrgY =20;       //链表节点在视图上的起始偏移位置
    int mNodeCx=50,mNodeCy=40;      //每个节点的尺寸

    public void onDraw(Canvas canvas){
        int x=mOrgX,y=mOrgY;
        Rect rectTitle=new Rect(x,y,canvas.getWidth()-x,y+40);
        drawText(canvas,mId+" "+mTitle,rectTitle,mPaint);
        y+=40;
        for(int i = 0; i< mNodes.length; i++){
            Rect rect=new Rect(x+mNodeCx*i,
                    y,
                    x+mNodeCx*(i+1),
                    y+mNodeCy);
            canvas.drawRect(rect,mPaint);//画节点矩形
            if(mNodes[i] != null) {
                if (i == mCurNode) {
                    drawText(canvas, "" + mNodes[i], rect, mPaintBlue);//画节点中的数据
                } else {
                    drawText(canvas, "" + mNodes[i], rect, mPaint);//画节点中的数据
                }
            }
            rect.offset(0,mNodeCx);
            drawText(canvas,""+i,rect,mPaint);//画节点下标。+
        }
    }
}
