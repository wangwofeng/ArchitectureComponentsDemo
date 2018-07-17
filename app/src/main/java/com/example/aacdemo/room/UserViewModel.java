package com.example.aacdemo.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

/**
 * Created by wangchun on 17-7-21.
 */

public class UserViewModel extends android.arch.lifecycle.AndroidViewModel{
//    private MutableLiveData<List<UserBean>> userList;
    private LiveData<List<UserBean>> userList;

    public UserViewModel(Application application) {
        super(application);
    }

    public LiveData<List<UserBean>> getUsers() {
        if (userList == null) {
//            userList = new MutableLiveData<>();
            loadUsers();
        }
        return userList;
    }

    private void loadUsers() {
//        List<UserBean> list = AppDatabase.DB.getUserDao().getAllUser();
//        userList.setValue(list);
        userList = AppDatabase.DB.getUserDao().getAllUserLiveData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("UserViewModel","onCleared user="+this);
    }
}
