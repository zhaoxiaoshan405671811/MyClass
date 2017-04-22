package com.example.touchevent;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    long firstClickTime = 0;
    long secondClickTime = 0;
    private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    //activity中只有事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Toast.makeText(MainActivity.this, "activity向下分发事件", Toast.LENGTH_SHORT).show();
        fun(ev, "activity");
//        return true;true为自己消费，不向下执行
        return super.dispatchTouchEvent(ev);//默认为false（即向下分发），但必须super.dispatchTouchEvent(ev)才可以，
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(MainActivity.this, "activity事件分发后执行", Toast.LENGTH_SHORT).show();
        fun(event, "activity的onTouchEvent");
//        return true;
        return super.onTouchEvent(event);
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                fun(event, "btn1");
                return true;
            }
        });
        btn1.setOnClickListener(this);
        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                fun(event, "btn2");
                return false;
            }
        });
        btn2.setOnClickListener(this);
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "长按", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setOnClickListener(this);
    }

    private void fun(MotionEvent event, String str) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(MainActivity.this, str + "按下：" + MotionEvent.ACTION_DOWN, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(MainActivity.this, str + "弹起：" + MotionEvent.ACTION_UP, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                Toast.makeText(MainActivity.this, str + "移动：" + MotionEvent.ACTION_MOVE, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                if (firstClickTime > 0) {
                    secondClickTime = SystemClock.uptimeMillis();
                    if (secondClickTime - firstClickTime < 500) {
                        Toast.makeText(this, "双击", Toast.LENGTH_SHORT).show();
                    }
                    firstClickTime = 0;
                    return;
                }
                firstClickTime = SystemClock.uptimeMillis();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                            firstClickTime = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(this, "单击", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
