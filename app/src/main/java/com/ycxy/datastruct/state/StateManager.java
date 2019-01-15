package com.ycxy.datastruct.state;

import java.util.ArrayList;

/**
*状态管理器，所有的状态存放在mStates中，任何时候只能画其中的一个状态
 * 此类相对稳定，以后要改的少。
 */
public class StateManager {
    private ArrayList<State> mStates=new ArrayList<State>();
    private int mCurState =0;//当前要画的状态在mStates中的索引值

    public StateManager(){
    }

    public ArrayList<State> getmStates() {
        return mStates;
    }

    public void addState(State state){
        state.setId(mStates.size());
        mStates.add(state);
    }

    public int getCurrent(){return mCurState;}

    public void setCurrent(int index){mCurState=index;}

    public State get(int index){return mStates.get(index);}

    public int getSize(){
        return mStates.size();
    }

    public State moveToFirst(){
        if(mStates.size()<1){
            return null;
        }
        mCurState=0;
        return mStates.get(mCurState);
    }

    public State moveToPrevious(){
        int index=mCurState-1;
        if(index<0 || index>=mStates.size()){
            return null;
        }
        return mStates.get(--mCurState);
    }

    public State moveToNext(){
        int index=mCurState+1;
        if(index<0 || index>=mStates.size()){
            return null;
        }
        return mStates.get(++mCurState);
    }

    public State moveToLast(){
        if(mStates.size()<1){
            return null;
        }
        mCurState=mStates.size()-1;
        return mStates.get(mCurState);
    }

    // 清空state
    public void destroymStates() {
        if(mStates != null) {
            mStates.clear();
        }
    }
}