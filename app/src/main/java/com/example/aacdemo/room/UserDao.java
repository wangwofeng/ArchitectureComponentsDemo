package com.example.aacdemo.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by wangchun on 17-7-24.
 */
@Dao
public interface UserDao {
    @Query("select * from user")
    List<UserBean> getAllUser();
    @Query("select * from user order by id desc")
    LiveData<List<UserBean>> getAllUserLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(UserBean user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void putAll(UserBean... user);

    @Insert
    void putAll(List<UserBean> list);
}
