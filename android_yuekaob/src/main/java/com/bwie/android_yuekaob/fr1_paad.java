package com.bwie.android_yuekaob;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/30.
 */

public class fr1_paad extends PagerAdapter {

    private ArrayList<String> list;
    private Context context;

    public fr1_paad(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView=new ImageView(context);
        x.image().bind(imageView,list.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView=new ImageView(context);
        x.image().bind(imageView,list.get(position));
        container.removeView(imageView);
    }



}
