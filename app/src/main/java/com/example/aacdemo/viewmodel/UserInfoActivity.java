package com.example.aacdemo.viewmodel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aacdemo.R;

public class UserInfoActivity extends Activity {
    private TextView name_textView;
    private TextView addr_textView;
    private TextView score_textView;
    private ProgressBar progressBar;
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
                    break;
                case 1:
                    int p=msg.arg1;
                    progressBar.setProgress(p);
                    break;
            }
        }
    };
}
