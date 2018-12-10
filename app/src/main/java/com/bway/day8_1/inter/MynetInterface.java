package com.bway.day8_1.inter;

import com.bway.day8_1.bean.ShowBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 择木 on 2018/12/6.
 */

public interface MynetInterface {

    @GET("nba/")
    Call<ShowBean> getShow(@QueryMap Map<String,String> map);
}
