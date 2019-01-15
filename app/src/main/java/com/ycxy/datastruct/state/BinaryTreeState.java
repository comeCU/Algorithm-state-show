package com.ycxy.datastruct.state;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.ycxy.datastruct.algorithm.BTNode;

/**
 * 工作重心，以后主要修改的代码在此类中。
 */
public class BinaryTreeState extends State{
    BTNode mRoot;               //二叉树根,用它表示一棵二叉树。
    int mSize;                  //二叉树结点个数,暂时没用上
    int mRadius=16;             //画图时，节点圆的半径
    int mMargin=8;              //相邻两个节点圆的水平和垂直间距

    //将传入的二叉树保存到当前状态对象，
    //clone表示是存放传入的二叉树的拷贝，还是直接引用它。
    public BinaryTreeState(BTNode root, int size, boolean clone){
        if(clone){
            mRoot=binaryTreeClone(root);
        }else{
            mRoot=root;
        }
        mSize=size;
    }

    //根据传入的二叉树root,拷贝并返回一棵全新的二叉树。
    BTNode binaryTreeClone(BTNode root){
        if(root==null){
            return null;
        }
        BTNode newNode=root.clone();
        newNode.mLeft=binaryTreeClone(root.mLeft);
        newNode.mRight=binaryTreeClone(root.mRight);
        return newNode;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(mRoot!=null) {
            int x=canvas.getWidth()/2;
            int y=mRadius+mMargin;
            canvas.drawText(mTitle,20,30,mPaint);
            drawTree(canvas, mRoot, x, y);
        }
    }

    //画整棵二叉树
    void drawTree(Canvas canvas,BTNode root,int x,int y){
        drawNode(canvas,root,x,y);
        if(root.mLeft!=null){
            drawEdge(canvas,x,y,true);
            drawTree(canvas,root.mLeft,x-mRadius*2-mMargin,y+mRadius*2+mMargin);
        }
        if(root.mRight!=null){
            drawEdge(canvas,x,y,false);
            drawTree(canvas,root.mRight,x+mRadius*2+mMargin,y+mRadius*2+mMargin);
        }
    }

    //画一个节点圆
    void drawNode(Canvas canvas,BTNode node,int x,int y){
        canvas.drawCircle(x,y,mRadius,mPaint);
        Rect rect=new Rect(x-mRadius,y-mRadius,x+mRadius,y+mRadius);
        drawText(canvas,String.valueOf(node.mData),rect,mPaint);
    }

    //画两个节点圆的连线，x,y为第一个圆的圆心,第二个圆在第一个圆的左下45度或右下45度。
    void drawEdge(Canvas canvas,int x,int y,boolean leftDown){
        int offStart=(int)(mRadius*0.707f);
        int offEnd=offStart+mMargin+(int)(mRadius*0.586f);
        if(leftDown){
            canvas.drawLine(x-offStart,y+offStart,
                    x-offEnd,y+offEnd,mPaint);

        }else {
            canvas.drawLine(x + offStart, y + offStart,
                    x + offEnd, y + offEnd, mPaint);
        }
    }
}
