package com.sutdy.work.rongyproject.Application;

import android.app.Application;
import android.util.Log;

import java.security.MessageDigest;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * 项目名称：RongYProject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/6/12 17:05
 * 修改备注
 */
public class Applicat extends Application {


    public static String App_Key;
    public static String Timestamp;
    public static String Signature;
    public static String Nonce;

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
        initHeader();
        initListener();
    }

    private void initListener() {
        RongIM.getInstance().enableNewComingMessageIcon(true);
        // 显示未读消息数目
        RongIM.getInstance().enableUnreadMessageIcon(true);
    }

    private void initHeader() {
        App_Key = "uwd1c0sxup0x1"; //开发者平台分配的 App Key。
        String App_Secret = "fT90r2qM3e7bj";
        Timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
        Log.i("timestamp",Timestamp);
        Nonce = String.valueOf(Math.floor(Math.random() * 1000000));//随机数，无长度限制。
        Log.i("Nonce",Nonce);
        Signature = sha1(App_Secret + Nonce + Timestamp);//数据签名。
        Log.i("Signature",Signature);
    }

    private void initView() {
        //初始化 融云
        RongIM.init(this);
    }

    private static String sha1(String data) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for (int i = 0; i < bits.length; i++) {
                int a = bits[i];
                if (a < 0) a += 256;
                if (a < 16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        } catch (Exception e) {

        }
        return buf.toString();
    }
}
