package com.example.aacdemo.viewmodel;

/**
 * Created by wangw_000 on 2018/3/20.
 */

public interface GetUserInfoCallback {
    public void onSuccess(UserInfoBean bean);
    public void onError(String msg);
    public void onLoading(int p);
}
