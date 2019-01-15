package com.ycxy.datastruct.algorithm;

/**
 * 供线性表使用的节点
 */
public class LTNode implements Cloneable{
    public int mData;
    public LTNode(int data){
        mData=data;
    }

    @Override
    public String toString(){
        return String.valueOf(mData);
    }

    @Override
    public LTNode clone(){
        LTNode newNode=new LTNode(mData);
        return newNode;
    }
}
