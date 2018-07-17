package com.example.aacdemo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by wangchun on 17-7-21.
 */
@Entity(tableName = "User")
public class UserBean {
    @PrimaryKey(autoGenerate = true)
    private int pk;
    private int id;
    private String name;
    @ColumnInfo(name = "Score",typeAffinity = 1)
    private int score;
    private String addr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name+"{id:"+id+",score:"+score+",addr:"+addr+"}";
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
