package com.example.aacdemo.livedata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aacdemo.R;

public class CarInfoActivity extends Activity implements View.OnClickListener{
    private TextView carName_textView;
    private TextView speed_textView;
    private Button detail_button;
    private SpeedChecker checker;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);
        initViews();
        id="111";   //要检查的车辆id
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
        carName_textView = findViewById(R.id.carName_textView);
        speed_textView = findViewById(R.id.speed_textView);
        detail_button = findViewById(R.id.detail_button);
        detail_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==detail_button){
            //跳转到车速详情页
            Intent intent = new Intent(this, CarSpeedActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
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
                    carName_textView.setText(bean.getCar());
                    speed_textView.setText("车速:"+bean.getSpeed()+" Km/h");
                    break;
            }
        }
    };
}
