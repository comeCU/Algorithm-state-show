package com.ycxy.datastruct.algorithm;

import com.ycxy.datastruct.R;
import com.ycxy.datastruct.Tools;
import com.ycxy.datastruct.state.SortState;

public class ShellSort extends Algorithm {

    int[] changeArr;
    int[] outArr;   // 用于真实交换希尔排序while

    @Override
    public void go() {
        outArr = changeArr.clone();
        int[] arr=changeArr.clone();        capture("初始状态",-1, -1,0);
        int i,j,k;
        int gap;	//gap是分组的步长
        int temp;	//希尔排序是在直接插入排序的基础上实现的,所以仍然需要哨兵
        for(gap=arr.length/2; gap>0; gap=gap/2){
            for(i=0; i<gap; i++){
                for(j=i+gap; j<arr.length; j=j+gap){	//单独一次的插入排序
                    if(arr[j] < arr[j - gap]){
                        temp = arr[j];	//哨兵
                        k = j - gap;
                        while(k>=0 && arr[k]>temp){     capture("交换前",k,k+gap, 1);    // 将将要交换的数据显示为红色
                            arr[k + gap] = arr[k];      capture("交换中",k,k+gap, 2); /* 交换数据并显示为红色*/ capture("交换后",k,k+gap, 3);    /* 恢复原色*/
                            k = k - gap;
                        }
                        arr[k + gap] = temp;
                    }
                }
            }
        }

    }

    @Override
    public String prepare(String args) {
        String[] inputArr = args.split(" ");
        try {
            changeArr = new int[inputArr.length];
            for (int i = 0; i< inputArr.length; i++) {
                changeArr [i]=Integer.parseInt(inputArr[i]);
            }
//            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public int getCodeResId() {
        return R.raw.shell_sort_code;
    }

    @Override
    public int getDescResId() {
        return R.raw.shell_sort_desc;
    }

    @Override
    public String getUsage() {
        return "输入希尔排序数组,以空格分开，如 8 9 1 7 2 3 5 4 6 0";
    }


    ////////////////////////
    //拍照方法，记录当前状态
    protected void capture(String title,int i,int j,int step){
        if(step==2) // 交换完成
        {
            int temp=outArr[i];
            outArr[i]=outArr[j];
            outArr[j]= temp;
        }
        SortState state = new SortState(title,outArr.clone(),i,j, step);
        state.setCodeLine(Tools.getCodeLine("capture"));

        if(i!=-1&&j!=-1)
        {
            state.addVariable("第"+i+"号位置",outArr[i]);
            state.addVariable("第"+j+"号位置",outArr[j]);
        }

        mStateManager.addState(state);
    }
}
