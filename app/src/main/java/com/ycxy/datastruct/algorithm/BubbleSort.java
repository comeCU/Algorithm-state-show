package com.ycxy.datastruct.algorithm;

import com.ycxy.datastruct.R;
import com.ycxy.datastruct.Tools;
import com.ycxy.datastruct.state.SortState;

public class BubbleSort extends Algorithm {
    //    private int min, point;
    int[] changeArr;

    @Override
    public void go() {
        int[] arr=changeArr.clone();        capture("初始状态",arr.clone(),-1, -1,0);
        for(int i=0;i<arr.length-1;i++){//外层循环控制排序趟数
            for(int j=0;j<arr.length-1-i;j++){//内层循环控制每一趟排序多少次
                if(arr[j]>arr[j+1]){        capture("交换前",arr.clone(),j,j+1, 1);    // 将将要交换的数据显示为红色
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;          capture("交换中",arr.clone(),j,j+1, 2); /* 交换数据并显示为红色*/ capture("交换后",arr.clone(),j,j+1, 3);    /* 恢复原色*/
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
        return R.raw.bubble_sort_code;
    }

    @Override
    public int getDescResId() {
        return R.raw.bubble_sort_desc;
    }

    @Override
    public String getUsage() {
        return "输入冒泡排序数组,以空格分开，如 5 4 2 3 1";
    }

    ////////////////////////
    //拍照方法，记录当前状态
    protected void capture(String title, int[] arr,int i,int j,int step){

        SortState state = new SortState(title,arr,i,j, step);
        state.setCodeLine(Tools.getCodeLine("capture"));

        if(i!=-1&&j!=-1)
        {
            state.addVariable("第"+i+"号位置",arr[i]);
            state.addVariable("第"+j+"号位置",arr[j]);
        }

        mStateManager.addState(state);
    }
}
