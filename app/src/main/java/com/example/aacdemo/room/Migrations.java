package com.example.aacdemo.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.util.Log;

/**
 * Created by wangchun on 17-7-24.
 */

public class Migrations {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.i("Migrations","@@ 数据库升级 MIGRATION_2_3");
            database.execSQL("CREATE TABLE IF NOT EXISTS `GroupTable` (`pk` INTEGER PRIMARY KEY AUTOINCREMENT, `id` INTEGER, `name` TEXT)");
        }
    };
}
