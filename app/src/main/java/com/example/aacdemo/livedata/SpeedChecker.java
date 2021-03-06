package com.example.aacdemo.livedata;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangw_000 on 2018/3/20.
 */

public class SpeedChecker {
    private String id;
    public void start(final String id, SpeedCheckerCallback callback){
        Log.i("SpeedChecker","## start id="+id);
        this.id=id;
        final String carName="奥迪A4L";
        int s= (int) (Math.random()*110)+10;
        CarSpeedBean bean = new CarSpeedBean();
        bean.setCar(carName);
        bean.setId(id);
        bean.setSpeed(s);
        Log.i("SpeedChecker","## run id="+id+", car="+carName+", s -> "+s);
        callback.onCheck(bean);
    }
    public void stop(){
        Log.i("SpeedChecker","## start id="+id);
    }
}
