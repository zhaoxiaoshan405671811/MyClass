package com.bwie.android_yuekaob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> list;
    private ArrayList<String> list1;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(IsHttpUtils.isNetworkConnected(MainActivity.this)){


        }else{

            AlertDialog.Builder aler=new AlertDialog.Builder(MainActivity.this);
            aler.setTitle("当前无网络");
            aler.setMessage("是否开启网络");
            aler.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));
                }
            });
            aler.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            aler.create();
            aler.show();
        }


        viewPager = (ViewPager) findViewById(R.id.view);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        initData();

        viewPager.setAdapter(new BaAd(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);



    }

    private void initData() {

        list = new ArrayList<Fragment>();
        Fragment1 f1=new Fragment1();
        Fragment2 f2=new Fragment2();
        Fragment3 f3=new Fragment3();
        Fragment4 f4=new Fragment4();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);


        list1 = new ArrayList<String>();
        list1.add("漫画");
        list1.add("发现");
        list1.add("v社区");
        list1.add("我的");

    }


    class BaAd extends FragmentPagerAdapter{

         public BaAd(FragmentManager fm) {
             super(fm);
         }

         @Override
         public Fragment getItem(int position) {
             return list.get(position);
         }

         @Override
         public int getCount() {
             return list.size();
         }

         @Override
         public CharSequence getPageTitle(int position) {
             return list1.get(position);
         }
     }


}
