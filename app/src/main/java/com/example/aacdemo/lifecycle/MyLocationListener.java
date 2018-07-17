package com.example.aacdemo.lifecycle;

import android.content.Context;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangw_000 on 2018/3/19.
 */

public class MyLocationListener {
    private Timer timer;
    private LocationCallback callback;
    private final String[] cities={"北京","上海","广州","郑州","沈阳","大连","成都","重庆"};
    public MyLocationListener(Context context, LocationCallback callback) {
        this.timer = new Timer();
        this.callback = callback;
    }

    public void start() {
        Log.i("MyLocationListener", "【start】");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int index = (int) (Math.random()*cities.length);
                Log.i("MyLocationListener", "timer.schedule location->"+cities[index]);
                LocationBean locationBean = new LocationBean();
                locationBean.setLocation(cities[index]);
                callback.onReceiveLocation(locationBean);
            }
        },1000, 3000);
    }

    public void stop() {
        Log.i("MyLocationListener", "【stop】");
        timer.cancel();
    }
}
