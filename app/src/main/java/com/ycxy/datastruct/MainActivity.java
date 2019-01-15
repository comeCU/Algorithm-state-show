package com.ycxy.datastruct;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycxy.datastruct.algorithm.Algorithm;
import com.ycxy.datastruct.view.DrawView;
import com.ycxy.datastruct.view.MyListView;
import com.ycxy.datastruct.state.State;
import com.ycxy.datastruct.state.StateManager;

import java.util.ArrayList;

/**
 * 此类的代码虽多，但是内容结构固定，稳定后基本不需要修改，不要花
 * 太多时间看此代码。
 */
public class MainActivity extends Activity implements Runnable{
    private Algorithm mAlgorithm;           //算法对象
    private MyListView mCodeListView;       //代码显示视图
    private DrawView mDrawView;             //运行效果视图
    private EditText mEditArgs;             //参数输入编辑框
    private TextView mTextVars;             //用于显示运行到某一步时的变量的视图
    private StateManager mStateManager;     //状态管理器,里面存储了所有状态，任何时候只会显示其中的一个状态
    private Thread mThread;                  //用于自动播放的子线程
    private Button mBtnOk,mBtnFirst,mBtnPrev,mBtnPlay,mBtnNext,mBtnLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawView = findViewById(R.id.draw_view);
        mEditArgs=findViewById(R.id.et_arguments);
        mTextVars=findViewById(R.id.tv_vars);
        mBtnOk=findViewById(R.id.btn_ok);
        mBtnFirst=findViewById(R.id.btn_first);
        mBtnPrev=findViewById(R.id.btn_previous);
        mBtnPlay=findViewById(R.id.btn_play);
        mBtnNext=findViewById(R.id.btn_next);
        mBtnLast=findViewById(R.id.btn_last);

        mStateManager=new StateManager();
        try {
            String name=getIntent().getStringExtra("AlgorithmName");
            mAlgorithm =(Algorithm)Class.forName(name).newInstance();
        } catch (Exception e) {
            Toast.makeText(this,"算法对象创建失败！",Toast.LENGTH_LONG).show();
        }
        mAlgorithm.setStateManager(mStateManager);
        mEditArgs.setHint(mAlgorithm.getUsage());
        //要演示的源代码，在res/raw文件夹中存放。
        String[] codes=Tools.resToLines(this,mAlgorithm.getCodeResId());
        mCodeListView = new MyListView(this,(ListView)findViewById(R.id.lv_code2),codes);
    }

    void showState(State state){
        mCodeListView.highlight(state.getCodeLine()-1);//代码行号以1开始
        mDrawView.draw(state);
        ArrayList<String> vars=state.getVariables();
        String lines="";
        for(String var:vars){
            if(lines.isEmpty()){
                lines=var;
            }else{
                lines=lines+"\n"+var;
            }
        }
        mTextVars.setText(lines);
    }

    public void doOk(View view) {
        mStateManager.destroymStates();  // 重复按执行将清空所有state

//        mStateManager.getmStates().clear(); // 重复按执行将清空所有state

        hideKeyboard();
        String errors= mAlgorithm.prepare(mEditArgs.getText().toString());
        if(errors != null){
            Toast.makeText(this,errors,Toast.LENGTH_LONG).show();
            return;
        }
        int lastIndex=mStateManager.getSize()-1;
        if(lastIndex<0){
            lastIndex=0;
        }
        mAlgorithm.go();
        mStateManager.setCurrent(lastIndex);
        this.doPlay(null);
    }

    public void doFirst(View view) {
        State state=mStateManager.moveToFirst();
        if(state != null) {
            showState(state);
        }
    }

    public void doPrevious(View view) {
        State state=mStateManager.moveToPrevious();
        if(state != null) {
            showState(state);
        }
    }

    public void doPlay(View view) {
        if(mThread == null){
            enableButtons(false);
            mThread=new Thread(this);
            mThread.start();
            mBtnPlay.setText("暂停");
        }else{
            mThread.interrupt();
            mThread=null;
            mBtnPlay.setText("播放");
            enableButtons(true);
        }
    }

    public void doNext(View view) {
        State state=mStateManager.moveToNext();
        if(state != null) {
            showState(state);
        }
    }

    public void doLast(View view) {
        State state=mStateManager.moveToLast();
        if(state != null) {
            showState(state);
        }
    }

    Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            State state=mStateManager.moveToNext();
            if(state != null) {
                showState(state);
            }else{
                mThread.interrupt();
                mThread=null;
                mStateManager.moveToFirst();
                mBtnPlay.setText("播放");
                enableButtons(true);
            }
        }
    };

    private void enableButtons(boolean enable){
        mBtnOk.setEnabled(enable);
        mBtnFirst.setEnabled(enable);
        mBtnPrev.setEnabled(enable);
        mBtnNext.setEnabled(enable);
        mBtnLast.setEnabled(enable);
    }

    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
                mHandler.sendEmptyMessage(0);
            }catch(Exception e){
                break;
            }
        }
    }

    void hideKeyboard(){
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }
}
