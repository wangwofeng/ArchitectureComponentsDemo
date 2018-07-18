package com.example.aacdemo.room;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aacdemo.R;

import java.util.ArrayList;
import java.util.List;

public class UserManagerActivity extends FragmentActivity implements LifecycleOwner,View.OnClickListener {
    private LifecycleRegistry lifecycleRegistry;
    private EditText name_editText;
    private EditText id_editText;
    private Button add_button;
    private TextView dbtool_textView;
    private ListView listView;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        lifecycleRegistry = new LifecycleRegistry(this);
        Log.i("DB.Version", AppDatabase.DB.getOpenHelper().getWritableDatabase().getVersion()+"");
        listView = findViewById(R.id.listView);
        add_button = findViewById(R.id.add_button);
        name_editText = findViewById(R.id.name_editText);
        id_editText = findViewById(R.id.id_editText);
        dbtool_textView = findViewById(R.id.dbtool_textView);
        add_button.setOnClickListener(this);
        dbtool_textView.setOnClickListener(this);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
        UserViewModel userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        final LiveData<List<UserBean>> liveData=userViewModel.getUsers();
        liveData.observe(this, new Observer<List<UserBean>>() {
            @Override
            public void onChanged(@Nullable List<UserBean> users) {
                Log.i("UserManagerActivity","@@ 数据更新了 users.size="+users.size());
                adapter.setData(users);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==add_button){
            //添加
            UserBean bean=new UserBean();
            int id=Integer.valueOf(id_editText.getText().toString());
            bean.setId(id);
            bean.setName(name_editText.getText().toString());
            bean.setScore((int) (Math.random()*100));
            AppDatabase.DB.getUserDao().save(bean);
        }
    }

    private class MyAdapter extends BaseAdapter{
        private List<UserBean> list=new ArrayList<>();
        @Override
        public int getCount() {
            return list!=null?list.size():0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public void setData(List<UserBean> list){
            this.list=list;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            UserBean userBean = list.get(position);
            TextView id_textView=null;
            TextView user_textView=null;
            TextView score_textView=null;
            if(convertView==null){
                convertView= LayoutInflater.from(UserManagerActivity.this).inflate(R.layout.item_user, null, false);
            }
            id_textView= (TextView) convertView.findViewById(R.id.id_textView);
            user_textView=(TextView) convertView.findViewById(R.id.user_textView);
            score_textView=(TextView) convertView.findViewById(R.id.score_textView);
            id_textView.setText(userBean.getId()+"");
            user_textView.setText(userBean.getName());
            score_textView.setText(userBean.getScore()+"");
            return convertView;
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
