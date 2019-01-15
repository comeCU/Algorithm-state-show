package com.ycxy.datastruct.algorithm;

import com.ycxy.datastruct.state.StateManager;

/**
 * 算法的基类，里面的定义了许多抽象方法，目的是提醒各个算法子类要实现这些方法。
 * 该类不需要改。
 */
public abstract class Algorithm {
    protected StateManager mStateManager;

    public void setStateManager(StateManager stateManager){
        mStateManager=stateManager;
    }

    //在子类中，对执行算法前的参数进行检查，如果成功，返回null,如果失败，返回错误提示。
    public abstract String prepare(String args);

    //在子类中，执行具体的算法
    public abstract void go();

    //在子类中，应该返回改算法在/res/raw下的代码文件id
    public abstract int getCodeResId();

    //在子类中，应该返回改算法在/res/raw下的描述文件id
    public abstract int getDescResId();

    //在子类中，应该返回参数输入格式说明,子类可以不实现该方法。
    public String getUsage(){return "";}
}
