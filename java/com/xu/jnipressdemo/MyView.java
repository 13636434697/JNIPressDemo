package com.xu.jnipressdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/*
* 压力表一般是竖起来的，没有这个进度条，所以自定义一个view
* */
public class MyView extends View {
	Paint paint;
	int pressure = 0;
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
	}

	public MyView(Context context) {
		super(context);
		paint = new Paint();
	}

	//调用完构造函数之后，调用这个方法
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//修改画笔颜色，在不同的压力值的时候表示不同的颜色
		if(pressure<40){
			paint.setColor(Color.GREEN);
		}else if(pressure <80){
			paint.setColor(Color.YELLOW);
		}else{
			paint.setColor(Color.RED);
		}
		//画一个长方形，不断改变高度，来表示压力（刚开始高度是0，就会看不到）
		canvas.drawRect(50, 200-pressure, 100, 200, paint);
		//写一下文字,左边和顶部的距离
		canvas.drawText("当前压力值:ֵ"+pressure, 50, 50, paint);
		//设置字体的大小
		paint.setTextSize(25);
	}
	//通过设置setPressure来设置高度，达到改变动态改变高度
	//c代码通过java调用的是这里的绘图，只是改变int的值，没有重新调用onDraw的方法。所以需要重绘一下
	public void setPressure(int pressure){
		this.pressure = pressure;
		//重绘视图，因为在子线程，不能修改界面
		//invalidate();
		//子线程重绘界面的方法
		postInvalidate();
	}
}
