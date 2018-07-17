package com.example.aacdemo.livedata;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aacdemo.R;

public class CarSpeedActivity extends Activity implements View.OnClickListener {
    private TextView speed_textView;
    private Button refresh_button;
    private SpeedChecker checker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
        initViews();
        doCheck();
    }
    private void doCheck(){
        String id = getIntent().getStringExtra("id");
        checker = new SpeedChecker();
        checker.start(id, new SpeedCheckerCallback() {
            @Override
            public void onCheck(CarSpeedBean bean) {
                Message msg=handler.obtainMessage(0, bean);
                handler.sendMessage(msg);
            }
        });
    }

    private void initViews(){
        speed_textView = findViewById(R.id.speed_textView);
        refresh_button = findViewById(R.id.refresh_button);
        refresh_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==refresh_button){
            //刷新车速
            doCheck();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        checker.stop();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    CarSpeedBean bean = (CarSpeedBean) msg.obj;
                    speed_textView.setText("车速:"+bean.getSpeed()+" Km/h");
                    break;
            }
        }
    };
}
