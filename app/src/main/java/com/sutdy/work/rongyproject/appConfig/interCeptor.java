package com.sutdy.work.rongyproject.appConfig;

import com.sutdy.work.rongyproject.Application.Applicat;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：RongYProject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/6/12 18:46
 * 修改备注
 */
public class interCeptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder origst = request.newBuilder();

        /*origst.addHeader("App_Key", Applicat.App_Key)
                .addHeader("Timestamp", Applicat.Timestamp)
                .addHeader("Nonce", Applicat.Nonce)
                .addHeader("Signature", Applicat.Signature)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .method(request.method(), request.body()); */
        origst.addHeader("App-Key", "uwd1c0sxup0x1")
                .addHeader("Timestamp", "1528876770")
                .addHeader("Nonce", "325570.0")
                .addHeader("Signature", "ca596ad46ab68e4d878949470c95bccc38126e2c")
                .method(request.method(), request.body());
        request=origst.build();
        return chain.proceed(request);
    }
}
