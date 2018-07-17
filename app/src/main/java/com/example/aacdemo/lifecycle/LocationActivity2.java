package com.example.aacdemo.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.aacdemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class LocationActivity2 extends Activity {
    private TextView currCity_textView;
    private MyLocationListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initViews();
        listener = new MyLocationListener(this, new LocationCallback() {
            @Override
            public void onReceiveLocation(LocationBean locationBean) {
                Message msg=handler.obtainMessage(0, locationBean.getLocation());
                handler.sendMessage(msg);
            }
        });
    }
    private void initViews(){
        currCity_textView = findViewById(R.id.currCity_textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //这里需要等待5秒钟才能开始获取地址
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.start();
            }
        },5000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        listener.stop();
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
}
