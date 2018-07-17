package com.example.aacdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aacdemo.lifecycle.LifecycleLocationActivity;
import com.example.aacdemo.lifecycle.LocationActivity;
import com.example.aacdemo.lifecycle.LocationActivity2;
import com.example.aacdemo.livedata.CarInfoActivity;
import com.example.aacdemo.livedata.LiveDataCarInfoActivity;
import com.example.aacdemo.room.UserManagerActivity;
import com.example.aacdemo.viewmodel.UserInfoActivity;
import com.example.aacdemo.viewmodel.UserInfoViewModelActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button location_button;
    private Button location2_button;
    private Button locationLivecycle_button;
    private Button userInfo_button;
    private Button userInfoViewModel_button;
    private Button carInfo_button;
    private Button carInfoLiveData_button;
    private Button room_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","@@ onCreate");
        initViews();
    }
    private void initViews(){
        location_button=findViewById(R.id.location_button);
        location2_button=findViewById(R.id.location2_button);
        locationLivecycle_button=findViewById(R.id.locationLivecycle_button);
        userInfo_button=findViewById(R.id.userInfo_button);
        userInfoViewModel_button=findViewById(R.id.userInfoViewModel_button);
        carInfo_button=findViewById(R.id.carInfo_button);
        carInfoLiveData_button=findViewById(R.id.carInfoLiveData_button);
        room_button=findViewById(R.id.room_button);

        location_button.setOnClickListener(this);
        location2_button.setOnClickListener(this);
        locationLivecycle_button.setOnClickListener(this);
        userInfo_button.setOnClickListener(this);
        userInfoViewModel_button.setOnClickListener(this);
        carInfo_button.setOnClickListener(this);
        carInfoLiveData_button.setOnClickListener(this);
        room_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==location_button){
            //处理生命周期：获取位置
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        }else if(v==location2_button){
            //处理生命周期：延时启动获取位置功能
            Intent intent = new Intent(this, LocationActivity2.class);
            startActivity(intent);
        }else if(v==locationLivecycle_button){
            //处理生命周期：使用Lifecycle处理
            Intent intent = new Intent(this, LifecycleLocationActivity.class);
            startActivity(intent);
        }else if(v==userInfo_button){
            //ViewModel:获取用户信息
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        }else if(v==userInfoViewModel_button){
            //ViewModel:获取用户信息，使用ViewModel
            Intent intent = new Intent(this, UserInfoViewModelActivity.class);
            startActivity(intent);
        }else if(v==carInfo_button){
            //LiveData演示-查看车辆信息
            Intent intent = new Intent(this, CarInfoActivity.class);
            startActivity(intent);
        }else if(v==carInfoLiveData_button){
            //LiveData演示-查看车辆信息，使用LiveData
            Intent intent = new Intent(this, LiveDataCarInfoActivity.class);
            startActivity(intent);
        }else if(v==room_button){
            Intent intent = new Intent(this, UserManagerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","@@ onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","@@ onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","@@ onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","@@ onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","@@ onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","@@ onRestart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("MainActivity","@@ onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MainActivity","@@ onRestoreInstanceState");
    }
}
