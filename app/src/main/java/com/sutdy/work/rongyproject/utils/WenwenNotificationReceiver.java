package com.sutdy.work.rongyproject.utils;

import android.content.Context;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * 项目名称：RongYProject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/6/14 11:42
 * 修改备注
 */
public class WenwenNotificationReceiver  extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}
