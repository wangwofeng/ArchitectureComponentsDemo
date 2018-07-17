package com.example.aacdemo.viewmodel;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangw_000 on 2018/3/20.
 */

public class HttpSender {
    public static void send(final String id, final GetUserInfoCallback callback){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int temp = 0;
                while (temp<=100){
                    Log.i("HttpSender","正在获取 ["+temp+"%]");
                    temp+=10;
                    callback.onLoading(temp);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                UserInfoBean bean = new UserInfoBean();
                bean.setAddr("北京市海淀区");
                bean.setName("霍金");
                bean.setScore(100);
                bean.setUserId(id);
                Log.i("HttpSender","获取成功 ["+bean.getName()+"]");
                callback.onSuccess(bean);
            }
        },1000);
    }
}
