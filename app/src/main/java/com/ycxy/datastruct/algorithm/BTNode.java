package com.ycxy.datastruct.algorithm;

/**
 * 供二叉树BinaryTree使用的节点类
 */
public class BTNode implements Cloneable{
    public int mData;
    public BTNode mLeft,mRight;

    public BTNode(int data){
        mData=data;
    }

    @Override
    public BTNode clone(){
        BTNode newNode=new BTNode(mData);
        newNode.mLeft=mLeft;
        newNode.mRight=mRight;
        return newNode;
    }

    public boolean less(int data){
        return mData<data;
    }

    public boolean great(int data){return mData>data;}
}
