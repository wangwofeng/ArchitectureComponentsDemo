package com.example.aacdemo.viewmodel;

/**
 * Created by wangw_000 on 2018/3/20.
 */

public class UserInfoBean {
    private String userId;
    private String name;
    private String addr;
    private int score;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
