# Algorithm-state-show

## 展示算法工作流程

运用安卓绘图，根据算法的设计思路达到相对应的演示效果。可以对整个动态绘制过程进行 **起始、上一步、播放、暂停、下一步、结尾** 操作。

<div align="center"> <img src="https://github.com/comeCU/myGitHubImages/raw/master/imgs_Algorithm-state-show/show.gif"/> </div>



## 主要类图设计

主要有View、State、Algorithm三大模块，对于一类算法（如排序算法）演示只需在Algorithm模块中添加相应的算法类，而无需修改其他的代码；如果需要添加另一类的算法（如线性表操作），则只需在State模块中添加ListState继承State类，在Algorithm中添加线性表操作算法类继承Algorithm。这里类似于一个桥接模式，桥梁的两端可以独立的变化，符合开闭原则。

![main](https://github.com/comeCU/myGitHubImages/raw/master/imgs_Algorithm-state-show/Main.jpg)

## 已实现部分

已实现选择排序、冒泡排序、希尔排序、线性表插入操作算法演示。如需测试可以在BriefActivity类中更改注释掉的代码依次运行安卓项目。

```java
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
    
    ......
```

