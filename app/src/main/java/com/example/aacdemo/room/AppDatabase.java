package com.example.aacdemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by wangchun on 17-7-24.
 */
@Database(entities = {UserBean.class}, version = 2, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase{
    public static AppDatabase DB;

    public abstract UserDao getUserDao();

    public static AppDatabase getInstance(Context context){
        if(DB!=null){
            return DB;
        }
        /**
         * 关于数据库迁移：
         * 数据库升级时，新增加表（Entity）或修改已存在表的结构，必须在Migrations中增加一个Migration，
         * 并指明升级的起止版本，使用Sql完成表的创建和更改，如此则可以保持原有数据。
         * 如果不引入Migration，则会导致整个数据库重建，数据全部丢失。
         * 如果只写了相应升级版本的Migration，而不在其中写sql，则会出现运行时报错。
         */
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "accDemo")
                .allowMainThreadQueries()
                .addMigrations(Migrations.MIGRATION_1_2)
                .build();
        DB=db;
        return db;
    }
}
