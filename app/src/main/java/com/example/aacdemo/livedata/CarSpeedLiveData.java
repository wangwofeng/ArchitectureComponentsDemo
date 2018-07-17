package com.example.aacdemo.livedata;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;
import android.util.Log;

/**
 * LiveData 是一个数据持有类，它持有一个值并且该值可以被观察。
 * 不同于普通的可观察者，LiveData 遵从应用组件的生命周期，
 * 这样 Observer 便可以指定一个其应该遵循的 Lifecycle。
 * 如果 Observer 所依附的 Lifecycle 处于 STARTED 或者 RESUMED 状态，
 * 则 LiveData 认为 Observer 处于活跃状态。
 * 可以感知组件生命周期的 LiveData 给我们提供了一种可能：可以在多个 Activity、
 * Fragment之间共享它。
 * Created by wangchun on 2018/3/20.
 */

public class CarSpeedLiveData extends LiveData<CarSpeedBean> {
    private static CarSpeedLiveData _Instance;
    private SpeedChecker checker;
    private String id;

    private CarSpeedLiveData(String id){
        this.id = id;
    }

    @MainThread
    public static CarSpeedLiveData get(String id) {
        if (_Instance == null) {
            synchronized (CarSpeedLiveData.class) {
                if(_Instance == null) {
                    _Instance = new CarSpeedLiveData(id);
                }
            }
        }
        return _Instance;
    }

    public void startCheck(){
        checker = new SpeedChecker();
        checker.start(id, new SpeedCheckerCallback() {
            @Override
            public void onCheck(CarSpeedBean bean) {
                postValue(bean);
//                setValue(bean);
            }
        });
    }
    /**
     * 调用该方法更新 LiveData 实例的值，并将此变更通知给处于活动状态的观察者。
     * @param value
     */
    @Override
    protected void setValue(CarSpeedBean value) {
        super.setValue(value);
    }

    /**
     * 当 LiveData 有一个处于活动状态的观察者时该方法被调用，这意味着需要开始从设备观察位置更新。
     */
    @Override
    protected void onActive() {
        super.onActive();
        Log.i("CarSpeedLiveData","@@ onActive");
    }

    /**
     * 当 LiveData 没有任何处于活动状态的观察者时该方法被调用。
     */
    @Override
    protected void onInactive() {
        super.onInactive();
        Log.i("CarSpeedLiveData","@@ onInactive");
    }
}
