package com.example.aacdemo.livedata;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aacdemo.R;

public class LiveDataCarSpeedActivity extends Activity implements View.OnClickListener,LifecycleOwner{
    private TextView speed_textView;
    private Button refresh_button;
    private LifecycleRegistry lifecycleRegistry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
        lifecycleRegistry=new LifecycleRegistry(this);
        initViews();
        String id = getIntent().getStringExtra("id");
        CarSpeedLiveData.get(id).observe(this, new Observer<CarSpeedBean>(){
            @Override
            public void onChanged(@Nullable CarSpeedBean bean) {
                Log.i("LiveDataCarSpeedAct","@@ onChanged");
                speed_textView.setText("车速:"+bean.getSpeed()+" Km/h");
            }
        });
//        CarSpeedLiveData.get(id).startCheck();
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
            String id = getIntent().getStringExtra("id");
            CarSpeedLiveData.get(id).startCheck();

        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
