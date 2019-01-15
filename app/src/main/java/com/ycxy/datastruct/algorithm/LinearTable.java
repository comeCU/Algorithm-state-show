package com.ycxy.datastruct.algorithm;

import com.ycxy.datastruct.R;
import com.ycxy.datastruct.Tools;
import com.ycxy.datastruct.state.LinearTableState;
import com.ycxy.datastruct.state.State;
import com.ycxy.datastruct.state.StateManager;

//线性表算法类,工作重心,注意，所有capture()调用都要写到某行代码右边。
public class LinearTable extends Algorithm{
    private LTNode[] mNodes =new LTNode[12];
    private int mSize=0;
    private int mPos,mData;//要插入的位置和数据

    //在pos位置插入数据，pos右边的所有数据右移一格。
    @Override
    public void go() {
        if(mSize == 0){//初始状态，强制pos为0
            mPos=0;                                                    capture("初始插入");
        }else{//在pos位置插入数据，pos右边的所有数据右移一格。
            int tailPos=mSize-1;                                     capture("准备右移","tailPos",tailPos);
            while(tailPos>=mPos){
                mNodes[tailPos+1]= mNodes[tailPos];                 capture("循环右移","tailPos",tailPos);
                tailPos--;                                              capture("位置递减","tailPos",tailPos);
            }
        }
        mNodes[mPos]=new LTNode(mData);                                 capture("插入结束");
        mSize++;
    }

    ////////////////////////下面代码跟算法无关，这部分不需要拷贝到res/raw的文件中
    //拍照方法，记录当前状态
    protected void capture(String title, Object ...vars ){
        LinearTableState state=new LinearTableState(mNodes,true);
        state.setTitle(title);
        state.setCodeLine(Tools.getCodeLine("capture"));
        state.addVariable("mPos",mPos);
        state.addVariable("mData",mData);
        state.setCurNode(mPos);
        for(int i=0; i<vars.length; i+=2){
            state.addVariable(vars[i].toString(),vars[i+1]);
        }
        mStateManager.addState(state);
    }

    //对插入前的参数进行检查，如果成功，返回null,如果失败，返回错误提示。
    @Override
    public String prepare(String args) {
        int pos,data;
        try {
            String[] arr=args.split(" |,");
            pos=Integer.valueOf(arr[0]);
            data=Integer.valueOf(arr[1]);
        }catch (Exception e){
            return "无效输入";
        }
        if(mSize>=mNodes.length){
            return "线性表已满！";
        }
        if(mSize>0 && (pos<0 || pos>= mSize)){
            return "pos超出范围!";
        }
        mPos=pos;
        mData=data;
        return null;
    }

    @Override
    public int getCodeResId() {
        return R.raw.linear_table_code;
    }

    @Override
    public int getDescResId() {
        return R.raw.linear_table_desc;
    }

    @Override
    public String getUsage(){
        return "输入位置和数据，如：0,5";
    }

}
