package com.example.my_erzhou_b;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<InfoBean> list = new ArrayList<InfoBean>();
    private ListView lv;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0;i<30;i++){
            InfoBean infoBean = new InfoBean();
            infoBean.setTitle("我是标题"+i);
            infoBean.setData("我是标题下的内容"+i);
            list.add(infoBean);
        }


        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);

        //跳转
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null){
                holder = new ViewHolder();
                convertView = View.inflate(MainActivity.this,R.layout.item,null);

                holder.image_item = (ImageView) convertView.findViewById(R.id.image_item);
                holder.title_item = (TextView) convertView.findViewById(R.id.title_item);
                holder.data_item = (TextView) convertView.findViewById(R.id.data_item);
                holder.btn_item = (Button) convertView.findViewById(R.id.btn_item);

                convertView.setTag(holder);

            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title_item.setText(list.get(position).getTitle());
            holder.data_item.setText(list.get(position).getData());

            holder.btn_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

    class ViewHolder{
        ImageView image_item;
        TextView title_item;
        TextView data_item;
        Button btn_item;
    }
}
