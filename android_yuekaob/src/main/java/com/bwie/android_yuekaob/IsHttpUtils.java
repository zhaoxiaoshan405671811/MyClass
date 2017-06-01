package com.bwie.android_yuekaob;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/5/30.
 */

public class IsHttpUtils {


    public static Boolean isNetworkConnected(Context context){
        if(context!=null){
            ConnectivityManager  mconnectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=mconnectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }



}
