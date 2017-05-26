package com.xu.jnipressdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/*
* 压力表的一个demo
*反射调用的流程，模拟和硬件交互的场景
*
*
* Caused by: java.lang.UnsatisfiedLinkError: Cannot load library: reloc_library[1306]:   100 cannot locate 'rand'
* 有这个错误，网上查询之后估计是编译版本的问题，以后需要将编译版本调低点试试
*
* */
public class MainActivity extends AppCompatActivity {
    static{
        System.loadLibrary("pressure");
    }
    //ProgressBar pb;
    //用自定义的进度条
    MyView pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pb = (ProgressBar) findViewById(R.id.pb);
        //用自定义的进度条
        pb = (MyView) findViewById(R.id.pb);
    }

    //按下按钮调用本地函数，实现检测的功能startMoniter
    public void start(View v){
        // while死循环阻塞在主线程里会堵塞，需要放在子线程里
        new Thread(){
            public void run() {
                //开始检测
                startMoniter();
            };
        }.start();

    }

    public void stop(View v){
        //停止监听
        stopMoniter();
    }

    //开始检测
    public native void startMoniter();
    //结束检测
    public native void stopMoniter();

    //设置进度值，和进度的最大值，
    // 回调这个方法，通过c的代码设置setProgress压力值（）的进度
    public void setPressure(int pressure ){
        //设置进度值
        //pb.setProgress(pressure);
        //用自定义的进度条
        pb.setPressure(pressure);
    }
}