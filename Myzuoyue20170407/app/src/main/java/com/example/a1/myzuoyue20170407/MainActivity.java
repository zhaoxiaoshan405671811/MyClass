package com.example.a1.myzuoyue20170407;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import adapter.MyAdapter;
import bean.MyBean;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<MyBean.ListBean> mList;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        MyAsyncTASK asyncTASK = new MyAsyncTASK();
        asyncTASK.execute("http://result.eolinker.com/t4uA2Q52c14f04fa8be3ec1721297782c021407c8b04922?uri=site");
    }

    private void initview() {
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,mList.get(position).getId()+"",Toast.LENGTH_LONG).show();

            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
    class MyAsyncTASK extends AsyncTask<String,Integer,String> {



        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                int code = connection.getResponseCode();
                if (code==200){
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                    StringBuilder builder = new StringBuilder();
                    String str;
                    while ((str=reader.readLine())!=null){
                        builder.append(str);
                    }
                    return builder.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            MyBean bean = gson.fromJson(s, MyBean.class);
            mList = bean.getList();
            mAdapter = new MyAdapter(mList,MainActivity.this);
            mListView.setAdapter(mAdapter);
        }
    }
}
