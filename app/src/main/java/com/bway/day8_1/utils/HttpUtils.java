package com.bway.day8_1.utils;

import android.os.Handler;

import com.bway.day8_1.bean.ShowBean;
import com.bway.day8_1.inter.Cantant;
import com.bway.day8_1.inter.ICallBack;
import com.bway.day8_1.inter.MynetInterface;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 择木 on 2018/12/6.
 */

public class HttpUtils {

   private static  HttpUtils instance;

   private Handler handler=new Handler();

   private OkHttpClient client;

   public HttpUtils(){
       client=new OkHttpClient.Builder()
               .writeTimeout(5000, TimeUnit.MILLISECONDS)
               .connectTimeout(5000, TimeUnit.MILLISECONDS)
               .readTimeout(5000, TimeUnit.MILLISECONDS)
               .build();

   }


    public static HttpUtils getInstance() {
       //单例双重锁
        if (instance==null){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance=new HttpUtils();
                }
            }
        }
        return instance;
    }
    int i=10;

   public void  doget(final ICallBack callBack){
       Retrofit retrofit=new Retrofit.Builder()
               .client(client)
               .baseUrl(Cantant.URL_BASE)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       MynetInterface mynetInterface = retrofit.create(MynetInterface.class);


       HashMap<String,String> map=new HashMap<>();
       map.put("key","71e58b5b2f930eaf1f937407acde08fe");
       map.put("num",i+"");
       i++;

       Call<ShowBean> showcall = mynetInterface.getShow(map);

       showcall.enqueue(new Callback<ShowBean>() {
           @Override
           public void onResponse(Call<ShowBean> call, Response<ShowBean> response) {
               List<ShowBean.NewslistBean> newslist = response.body().getNewslist();
                if (response!=null &&response.isSuccess()){
                    callBack.onSuccess(newslist);
                }
           }

           @Override
           public void onFailure(Call<ShowBean> call, Throwable t) {

           }
       });

   }
}
