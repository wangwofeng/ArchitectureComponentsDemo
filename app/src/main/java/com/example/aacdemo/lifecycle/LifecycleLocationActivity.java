package com.example.aacdemo.lifecycle;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.aacdemo.R;

public class LifecycleLocationActivity extends Activity implements LifecycleOwner{
    private TextView currCity_textView;
    private MyLivecycleLocationListener listener;
    private LifecycleRegistry lifecycleRegistry;    //可以直接使用父类的lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        lifecycleRegistry = new LifecycleRegistry(this);
        initViews();
        listener = new MyLivecycleLocationListener(this, getLifecycle(), new LocationCallback() {
            @Override
            public void onReceiveLocation(LocationBean locationBean) {
                Message msg=handler.obtainMessage(0, locationBean.getLocation());
                handler.sendMessage(msg);
            }
        });

        lifecycleRegistry.addObserver(listener);
    }
    private void initViews(){
        currCity_textView = findViewById(R.id.currCity_textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //***将等待5秒钟才能开始获取地址的代码移动到listener
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String location= (String) msg.obj;
                    currCity_textView.setText(location);
            }
        }
    };

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
