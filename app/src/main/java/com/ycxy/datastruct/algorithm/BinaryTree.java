package com.ycxy.datastruct.algorithm;

import com.ycxy.datastruct.R;
import com.ycxy.datastruct.Tools;
import com.ycxy.datastruct.state.BinaryTreeState;
import com.ycxy.datastruct.state.StateManager;

public class BinaryTree extends Algorithm{
    private BTNode mRoot=null;//二叉树根
    private int mSize=0;//二叉树结点个数
    private int mData;//临时存储要插入的数据

    public void go() {
        mRoot=insert(mRoot,mData);                              capture("插入结束");
    }

    BTNode insert(BTNode node, int data){
        if(node==null){
            mSize++;                                            capture("创建新节点");
            return new BTNode(data);
        }else if(node.great(data)){
            node.mLeft=insert(node.mLeft,data);                 capture("降左支");
        }else if(node.less(data)){
            node.mRight=insert(node.mRight,data);               capture("降右支");
        }
        return node;
    }

    ////////////////////////下面代码跟算法无关，这部分不需要拷贝到res/raw的文件中
    //拍照方法，记录当前状态
    void capture(String title, Object ...vars ){
        BinaryTreeState state=new BinaryTreeState(mRoot,mSize,true);
        state.setTitle(title);
        state.setCodeLine(Tools.getCodeLine("capture"));
        state.addVariable("mSize",mSize);
        for(int i=0; i<vars.length; i+=2){
            state.addVariable(vars[i].toString(),vars[i+1]);
        }
        mStateManager.addState(state);
    }

    //对插入前的参数进行检查，如果成功，返回null,如果失败，返回错误提示。
    public String prepare(String args) {
        try {
            int data=Integer.valueOf(args);
            mData=data;
            return null;
        }catch (Exception e){
            return "无效输入";
        }
    }

    public int getCodeResId() {
        return R.raw.binary_tree_code;
    }

    public int getDescResId() {
        return R.raw.binary_tree_desc;
    }

    public String getUsage(){
        return "输入一个整数";
    }
}
