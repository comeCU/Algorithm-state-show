package com.ycxy.datastruct;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 不要看里面的代码，参考MainActivity中的调用即可。
 */
public class Tools {
    public static String resToString(Context context, int rawResId){
        StringBuffer sb=new StringBuffer();
        try {
            InputStream is=context.getResources().openRawResource(rawResId);
            BufferedReader brf= new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while(true){
                String line=brf.readLine();
                if(line == null) {
                    break;
                }
                sb.append(line);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String[] resToLines(Context context, int rawResId){
        ArrayList<String> lines=new ArrayList<String>();
        try {
            InputStream is=context.getResources().openRawResource(rawResId);
            BufferedReader brf= new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while(true){
                String line=brf.readLine();
                if(line == null) {
                    break;
                }
                lines.add(line);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[lines.size()]);
    }

    //获取调用方法methodName的代码的行号
    public static int getCodeLine(String methodName){
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        boolean found=false;
        for(StackTraceElement element : elements) {
            String name= element.getMethodName();
            if(name.startsWith(methodName)){
                found=true;
            }else if(found){
                return element.getLineNumber();
            }
        }
        return -1;
    }

}
