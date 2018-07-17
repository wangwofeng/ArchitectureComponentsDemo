package com.example.aacdemo.livedata;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aacdemo.R;

public class LiveDataCarInfoActivity extends Activity implements View.OnClickListener,LifecycleOwner{
    private TextView carName_textView;
    private TextView speed_textView;
    private Button detail_button;
    private String id;
    private LifecycleRegistry lifecycleRegistry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);
        lifecycleRegistry=new LifecycleRegistry(this);
        initViews();
        id="111";   //要检查的车辆id
        CarSpeedLiveData.get(id).observe(this, new Observer<CarSpeedBean>(){
            @Override
            public void onChanged(@Nullable CarSpeedBean bean) {
                Log.i("LiveDataCarInfoActivity","@@ onChanged");
                carName_textView.setText(bean.getCar());
                speed_textView.setText("车速:"+bean.getSpeed()+" Km/h");
            }
        });
        CarSpeedLiveData.get(id).startCheck();
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
            Intent intent = new Intent(this, LiveDataCarSpeedActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
