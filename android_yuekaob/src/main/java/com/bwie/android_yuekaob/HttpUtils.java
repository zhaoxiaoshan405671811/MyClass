package com.bwie.android_yuekaob;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/5/30.
 */

public class HttpUtils {

    private LodefaceData lodefaceData;

    public HttpUtils(LodefaceData lodefaceData,String url) {
        this.lodefaceData = lodefaceData;
        LodeHttpData(url);
    }

    public void LodeHttpData(String url){

            RequestParams requestParams=new RequestParams(url);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Gson gson=new Gson();
                    Benin1 benin1 = gson.fromJson(result, Benin1.class);
                    lodefaceData.FaceData(benin1);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }


        public interface LodefaceData{
              void FaceData(Benin1 benin1);
       }

}
