package com.example.aacdemo.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aacdemo.R;

public class UserInfoViewModelActivity extends FragmentActivity{
    private TextView name_textView;
    private TextView addr_textView;
    private TextView score_textView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initViews();
        //获取ViewModel
        //仅支持FragmentActivity(v4)，Fragment(v4)，AppCompatActivity
        UserInfoViewModel model = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        model.getUser().observe(this, new Observer<UserInfoBean>(){
            @Override
            public void onChanged(@Nullable UserInfoBean bean) {
                //name_textView.setText(bean.getName());
                addr_textView.setText(bean.getAddr());
                score_textView.setText(bean.getScore()+"");
            }
        });
        model.getUserNameLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                name_textView.setText(s);
            }
        });

    }
    private void initViews(){
        name_textView = findViewById(R.id.name_textView);
        addr_textView = findViewById(R.id.addr_textView);
        score_textView = findViewById(R.id.score_textView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

}
