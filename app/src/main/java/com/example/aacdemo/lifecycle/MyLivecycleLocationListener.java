package com.example.aacdemo.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import static android.arch.lifecycle.Lifecycle.State.STARTED;

/**
 * Created by wangw_000 on 2018/3/19.
 */

public class MyLivecycleLocationListener implements LifecycleObserver {
    private Timer timer;
    private Timer startTimer;
    private LocationCallback callback;
    private Lifecycle lifecycle;
    private final String[] cities={"北京","上海","广州","郑州","沈阳","大连","成都","重庆"};
    public MyLivecycleLocationListener(Context context, Lifecycle lifecycle, LocationCallback callback) {
        this.timer = new Timer();
        this.callback = callback;
        this.lifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        Log.i("MyLivecycleLocListener", "【start】");
        //这里需要等待5秒钟才能开始获取地址
        startTimer = new Timer();
        startTimer.schedule(new TimerTask() {
            @Override
            public void run() {timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (lifecycle.getCurrentState().isAtLeast(STARTED)) {
                        int index = (int) (Math.random() * cities.length);
                        Log.i("MyLivecycleLocListener", "timer.schedule location->" + cities[index]);
                        LocationBean locationBean = new LocationBean();
                        locationBean.setLocation(cities[index]);
                        callback.onReceiveLocation(locationBean);
                    }else{
                        Log.i("MyLivecycleLocListener", "timer.schedule 因状态异常不能启动");
                    }
                }
            },1000, 3000);
            }
        },5000);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        Log.i("MyLivecycleLocListener", "【stop】");
        startTimer.cancel();
        timer.cancel();
    }
}
