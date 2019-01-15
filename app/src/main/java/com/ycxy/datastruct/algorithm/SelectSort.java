package com.ycxy.datastruct.algorithm;

import com.ycxy.datastruct.R;
import com.ycxy.datastruct.Tools;
import com.ycxy.datastruct.state.SortState;

/**
 * @author dong
 */


public class SelectSort extends Algorithm {
    private int[] inputArr = null;
//    private int min, point;
    int[] changeArr;
    SortState state;

    @Override
    public void go() {
        int[] arr=changeArr.clone();
        int i, j, min=0;                capture("初始状态",arr.clone(),-1, -1,0);    //
        // 总共要经过 N-1 轮比较
        for (i = 0; i < arr.length - 1; i++) {
            min = i;
            // 每轮需要比较的次数 N-i
            for (j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {             capture("交换前",arr.clone(),i,min, 1);    // 将将要交换的数据显示为红色
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;         capture("交换中",arr.clone(),i,min, 2); /* 交换数据并显示为红色*/ capture("交换后",arr.clone(),i,min, 3);    /* 恢复原色*/
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
        return R.raw.select_sort_code;
    }

    @Override
    public int getDescResId() {
        return R.raw.select_sort_desc;
    }

    @Override
    public String getUsage() {
        return "输入选择排序数组,以空格分开，如 4 2 3 6 5";
    }


    ////////////////////////
    //拍照方法，记录当前状态
    protected void capture(String title, int[] arr,int i,int j,int step){

        state = new SortState(title,arr,i,j, step);
        state.setCodeLine(Tools.getCodeLine("capture"));

        if(i!=-1&&j!=-1)
        {
            state.addVariable("第"+i+"号位置",arr[i]);
            state.addVariable("第"+j+"号位置",arr[j]);
        }

        mStateManager.addState(state);
    }


}
