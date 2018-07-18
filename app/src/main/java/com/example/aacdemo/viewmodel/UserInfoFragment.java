package com.example.aacdemo.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aacdemo.MyApplication;
import com.example.aacdemo.R;

public class UserInfoFragment extends Fragment{
    private View view;
    private TextView name_textView;
    private TextView addr_textView;
    private TextView score_textView;
    private TextView refresh_textView;
    private ProgressBar progressBar;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name_textView = view.findViewById(R.id.name_textView);
        addr_textView = view.findViewById(R.id.addr_textView);
        score_textView = view.findViewById(R.id.score_textView);
        progressBar = view.findViewById(R.id.progressBar);
        refresh_textView = view.findViewById(R.id.refresh_textView);
        refresh_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.mApp,"点击刷新啦 -- 刷不了啊",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view=inflater.inflate(R.layout.fragment_userinfo, null);
    }

    public void refresh(UserInfoBean bean){
        if(isVisible()) {
            name_textView.setText(bean.getName());
            addr_textView.setText(bean.getAddr());
            score_textView.setText(bean.getScore() + "");
        }
    }
    public void refreshProgress(int p){
        if(isVisible()) {
            progressBar.setProgress(p);
        }
    }
}
