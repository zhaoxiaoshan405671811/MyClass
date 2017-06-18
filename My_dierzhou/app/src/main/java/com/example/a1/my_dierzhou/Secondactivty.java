package com.example.a1.my_dierzhou;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Secondactivty extends AppCompatActivity {
    private TextView text_name;
    private TextView text_pass;
    private TextView text_id_jizhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivty);
        initview();//初始化控件
        data();//初始化数据
        jingting();//设置监听
    }

    private void jingting() {
        text_id_jizhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击设置背景颜色
                text_id_jizhu.setBackgroundColor(Color.RED);
                 //实例化一个Toast类
                Toast toast = new Toast(Secondactivty.this);
                //设置吐司的时间
                toast.setDuration(Toast.LENGTH_SHORT);
                //自己定义个布局 ，一个textview的控件
                View view = View.inflate(Secondactivty.this, R.layout.toast_custom,null);
                TextView tvPrompt = (TextView)view.findViewById(R.id.tv_prompt);
                //设置吐司的内容
                tvPrompt.setText("查看是否为记住密码");
                //吧view设置在控件上
                toast.setView(view);
                //设置吐司的位置
                //CENTER 中间  根据设置的是在中间的还是下边的设置x轴和Y轴
                toast.setGravity(Gravity.CENTER, 0, 0);
                //展示
                toast.show();
            }
        });
    }

    private void data() {
        //得到意图传过来的值
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String pass = intent.getStringExtra("pass");
        text_name.setText(name);
        text_pass.setText(pass);
    }

    private void initview() {
        text_name = (TextView) findViewById(R.id.text_name);
        text_pass = (TextView) findViewById(R.id.text_pass);
        text_id_jizhu = (TextView) findViewById(R.id.text_id_jizhu);
    }
}
