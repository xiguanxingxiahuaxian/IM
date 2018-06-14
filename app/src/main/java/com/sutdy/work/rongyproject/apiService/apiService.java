package com.sutdy.work.rongyproject.apiService;

import com.sutdy.work.rongyproject.Application.Applicat;
import com.sutdy.work.rongyproject.appConfig.config;
import com.sutdy.work.rongyproject.bean.TokenBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 项目名称：RongYProject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/6/12 17:18
 * 修改备注
 */
public interface apiService {
    @FormUrlEncoded
    @POST("user/getToken.json")
    Call<TokenBean>getToken(
            @Field("userId") String userid,
            @Field("name") String name,
            @Field("portraitUri") String uri);
}
