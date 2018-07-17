package com.example.aacdemo.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by wangc on 2018/3/20.
 */

public class UserInfoViewModel extends ViewModel {
    private MutableLiveData<UserInfoBean> user;
    /* 转换LiveData中的值（Transform LiveData）
     * 这里的变换是指在LiveData的数据被分发到各个组件之前转换值的内容，
     * 各个组件收到的是转换后的值，但是LiveData里面数据本身的值并没有改变。
     */
    private LiveData<String> userNameLiveData;
    public LiveData<UserInfoBean> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
            loadUser();
        }
        return user;
    }
    public LiveData<String> getUserNameLiveData(){
        if(user==null){
            getUser();
        }
        if(userNameLiveData == null){
            userNameLiveData= Transformations.map(user, new Function<UserInfoBean, String>() {
                @Override
                public String apply(UserInfoBean userInfoBean) {
                    return userInfoBean.getName()+"(转)";
                }
            });
        }
        return userNameLiveData;
    }

    private void loadUser() {
        Log.i("UserInfoViewModel","## loadUser");
        HttpSender.send("222", new GetUserInfoCallback() {
            @Override
            public void onSuccess(UserInfoBean bean) {
                Log.i("UserInfoViewModel","loadUser onSuccess");
//                Message msg=handler.obtainMessage(0, bean);
//                handler.sendMessage(msg);
//                user.setValue(bean);
                user.postValue(bean);
            }

            @Override
            public void onError(String msg) {
                Log.i("UserInfoViewModel","loadUser onError");

            }

            @Override
            public void onLoading(int p) {
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    UserInfoBean bean= (UserInfoBean) msg.obj;
                    user.setValue(bean);
                    break;
            }
        }
    };

    @Override
    protected void onCleared() {
        super.onCleared();
        handler.removeCallbacksAndMessages(null);
        Log.i("UserInfoViewModel","## onCleared");
    }
}
