package com.example.aacdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.aacdemo.room.AppDatabase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;

public class MyApplication extends Application {

    public static MyApplication mApp;

    public static MyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) this
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            String processName = appProcess.processName;
            if (appProcess.pid == pid && processName.equals(getPackageName())) {
                //主进程,默认主进程名称=包名
                doInMainProcess();
                break;
            }
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 只在主线程中执行的动作
     */
    protected void doInMainProcess() {
        AppDatabase.getInstance(this);
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }

    /**
     * 捕获错误信息的handler
     */
    private UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            String info = null;
            ByteArrayOutputStream baos = null;
            PrintStream printStream = null;
            try {
                baos = new ByteArrayOutputStream();
                printStream = new PrintStream(baos);
                ex.printStackTrace(printStream);
                byte[] data = baos.toByteArray();
                info = new String(data);
                data = null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (printStream != null) {
                        printStream.close();
                    }
                    if (baos != null) {
                        baos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Logger.e("uncaughtExceptionHandler", e.toString());
                }
            }
            Log.e("uncaughtException", info);
            Log.e("uncaughtException", "This is:" + thread.getName()
                    + ",Message:" + ex.getMessage());
        }

        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(
                    "Clone the UncaughtExceptionHandler, You can not, hmm");
        }
    };

}
