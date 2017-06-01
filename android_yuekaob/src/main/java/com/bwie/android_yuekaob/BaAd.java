package com.bwie.android_yuekaob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/31.
 */

public class BaAd extends BaseAdapter {
    private ArrayList<Benin1> list;
    private Context context;

    public BaAd(ArrayList<Benin1> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.get(0).getResult().getIndexProducts().size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(0).getResult().getIndexProducts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Get1 g;
        if(convertView==null){
         convertView= LayoutInflater.from(context).inflate(R.layout.it,null);
          g=new Get1();
         g.imageView= (ImageView) convertView.findViewById(R.id.it_image);
            g.textView= (TextView) convertView.findViewById(R.id.it_text);
          convertView.setTag(g);
        }else{
            g= (Get1) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(0).getResult().getIndexProducts().get(position).getPic(),g.imageView);
        g.textView.setText(list.get(0).getResult().getIndexProducts().get(position).getName());
        return convertView;
    }
    class Get1{
       ImageView imageView;
        TextView textView;
    }
}
