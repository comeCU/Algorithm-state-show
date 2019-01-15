package com.ycxy.datastruct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ycxy.datastruct.algorithm.Algorithm;
import com.ycxy.datastruct.algorithm.BinaryTree;
import com.ycxy.datastruct.algorithm.BubbleSort;
import com.ycxy.datastruct.algorithm.LinearTable;
import com.ycxy.datastruct.algorithm.SelectSort;
import com.ycxy.datastruct.algorithm.ShellSort;

//如果要测试二叉树，看下面注释
public class BriefActivity extends Activity {
    final String TAG="BriefActivity";
    private TextView tv_content;
    private ListView lv_code;
    private String descs;
    private String[] codes;//算法代码
//    private Algorithm mAlgorithm=new LinearTable();
    //如果要测试二叉树，把上面那句改成下面这句，其它地方都不需要动。
//    private Algorithm mAlgorithm=new BinaryTree();

//    private Algorithm mAlgorithm = new SelectSort();    // 选择排序

//    private Algorithm mAlgorithm = new BubbleSort();    // 冒泡排序

    private Algorithm mAlgorithm = new ShellSort();     // 希尔排序

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brief);

        tv_content = findViewById(R.id.tv_content);
        lv_code = findViewById(R.id.lv_code);

        descs= Tools.resToString(this,mAlgorithm.getDescResId());
        codes=Tools.resToLines(this,mAlgorithm.getCodeResId());

        tv_content.setText(Html.fromHtml(descs ));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item,R.id.tv_code, codes);
        lv_code.setAdapter(adapter);
    }

    public void onClick(View view){
        Intent intent = new Intent(BriefActivity.this, MainActivity.class );
        Class<?> clsInfo=mAlgorithm.getClass();
        intent.putExtra("AlgorithmName",mAlgorithm.getClass().getCanonicalName());
        startActivity(intent);
    }
}
