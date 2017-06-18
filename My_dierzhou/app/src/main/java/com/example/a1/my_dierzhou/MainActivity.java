package com.example.a1.my_dierzhou;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edittext_name;
    private EditText edittext_pass;
    private TextView textview_tiaokuan;
    private Button button_denglu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        Ziti();
        tiaozuang();
    }
    private void tiaozuang() {
        button_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Secondactivty.class);
                intent.putExtra("name",edittext_name.getText().toString());
                intent.putExtra("pass",edittext_pass.getText().toString());
                startActivity(intent);
            }
        });
    }

    //对字体颜色的变化
    private void Ziti() {
        String count="我以阅读并同意服务条款";
        SpannableString spannablestring=new SpannableString(count);
        spannablestring.setSpan(new ForegroundColorSpan(Color.RED),7,11, Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        textview_tiaokuan.setText(spannablestring);
    }


    private void initview() {
        edittext_name = (EditText) findViewById(R.id.edittext_name);
        edittext_pass = (EditText) findViewById(R.id.edittext_pass);
        button_denglu = (Button) findViewById(R.id.button_denglu);
        textview_tiaokuan = (TextView) findViewById(R.id.textview_tiaokuan);

    }
}
