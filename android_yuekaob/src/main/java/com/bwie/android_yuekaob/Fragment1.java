package com.bwie.android_yuekaob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/5/30.
 */

public class Fragment1 extends Fragment{


    private ViewPager viewPager;
    private int count=0;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            viewPager.setCurrentItem(count);
            if(count==5){
                count=0;
            }


        }
    };
    private GridView gridView;
    private TextView textView;
    private Timer time;
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) getActivity().findViewById(R.id.f1_view);
        gridView = (GridView) getActivity().findViewById(R.id.grid);
        textView = (TextView) getActivity().findViewById(R.id.f1_text1);
        ll = (LinearLayout) getActivity().findViewById(R.id.ll);
        LodeData("http://www.babybuy100.com/API/getShopOverview.ashx");


        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
            handler.sendEmptyMessage(0);
            }

        },0,3000);




    }



    public void LodeData(String url){

        HttpUtils httpUtils=new HttpUtils(new HttpUtils.LodefaceData() {
            @Override
            public void FaceData(Benin1 benin1) {
                ArrayList<String> list=new ArrayList<String>();
                ArrayList<Benin1> list1=new ArrayList<Benin1>();
                for (int i = 0; i < benin1.getResult().getBrands().get(0).getProducts().size(); i++) {
                    list.add(benin1.getResult().getBrands().get(0).getProducts().get(i).getPic());
                }
                list1.add(benin1);

                for (int i = 0; i < benin1.getResult().getBrands().get(0).getProducts().size(); i++) {

                    //创建一个View对象
                    View view = new View(getActivity());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                    params.rightMargin = 10;
                    params.leftMargin = 10;
                    view.setLayoutParams(params);
                    if(i == 0){
                        view.setBackgroundResource(R.mipmap.jog_tab_target_yellow);
                    }else{
                        view.setBackgroundResource(R.mipmap.jog_tab_target_gray);
                    }
                    //view.setBackgroundColor(Color.BLACK);
                    //将这个View挂载到ll这个容器中
                    ll.addView(view);
//                    dotList.add(view);
                }


                viewPager.setAdapter(new fr1_paad(list,getActivity()));
                gridView.setAdapter(new BaAd(list1,getActivity()));
                final String[]  strs1=new String[benin1.getResult().getCategory().size()];
                final String[]  strs2=new String[benin1.getResult().getNations().size()];
                for (int i = 0; i < benin1.getResult().getCategory().size() ; i++) {

                    strs1[i]=benin1.getResult().getCategory().get(i).getName();
                }
                for (int i = 0; i < benin1.getResult().getNations().size() ; i++) {

                    strs2[i]=benin1.getResult().getNations().get(i).getName();
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent it=new Intent(getActivity(),OneActivity.class);
                        it.putExtra("category",strs1);
                        it.putExtra("nations",strs2);
                        getActivity().startActivity(it);

                    }
                });

//               getActivity().runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
//
//                   }
//               });
            }

        },url);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();
    }
}
