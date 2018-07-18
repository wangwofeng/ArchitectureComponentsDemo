package com.example.aacdemo.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aacdemo.MyApplication;
import com.example.aacdemo.R;

public class UserInfoActivity extends FragmentActivity {
    private TextView name_textView;
    private TextView addr_textView;
    private TextView score_textView;
    private TextView refresh_textView;
    private ProgressBar progressBar;
    private UserInfoFragment fragment1;
    private UserInfoFragment fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initViews();
        loadUserInfo();
    }
    private void initViews(){
        name_textView = findViewById(R.id.name_textView);
        addr_textView = findViewById(R.id.addr_textView);
        score_textView = findViewById(R.id.score_textView);
        progressBar = findViewById(R.id.progressBar);
        refresh_textView = findViewById(R.id.refresh_textView);
        fragment1 = (UserInfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragment2 = (UserInfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        refresh_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.mApp,"点击刷新啦",Toast.LENGTH_LONG).show();
                loadUserInfo();
            }
        });
    }

    private void loadUserInfo(){
        HttpSender.send("222", new GetUserInfoCallback() {
            @Override
            public void onSuccess(UserInfoBean bean) {
                Log.i("UserInfoActivity","loadUserInfo onSuccess");
                Message msg=handler.obtainMessage(0, bean);
                handler.sendMessage(msg);
            }

            @Override
            public void onError(String msg) {
                Log.i("UserInfoActivity","loadUserInfo onError");

            }

            @Override
            public void onLoading(int p) {
                Message msg=handler.obtainMessage(1);
                msg.arg1=p;
                handler.sendMessage(msg);
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    UserInfoBean bean= (UserInfoBean) msg.obj;
                    name_textView.setText(bean.getName());
                    addr_textView.setText(bean.getAddr());
                    score_textView.setText(bean.getScore()+"");
                    if(fragment1!=null){
                        fragment1.refresh(bean);
                    }
                    if(fragment2!=null){
                        fragment2.refresh(bean);
                    }
                    break;
                case 1:
                    int p=msg.arg1;
                    progressBar.setProgress(p);
                    if(fragment1!=null){
                        fragment1.refreshProgress(p);
                    }
                    if(fragment2!=null){
                        fragment2.refreshProgress(p);
                    }
                    break;
            }
        }
    };
}
