package cn.codingblock.optimize.uncaught_exception;

import android.util.Log;

/**
 * Created by liuwei on 18/1/12.
 */

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = MyUncaughtExceptionHandler.class.getSimpleName();

    private static MyUncaughtExceptionHandler sInstance;

    private MyUncaughtExceptionHandler() {

    }

    public static MyUncaughtExceptionHandler getInstance() {
        if (sInstance == null) {
            synchronized (MyUncaughtExceptionHandler.class) {
                if (sInstance == null) {
                    sInstance = new MyUncaughtExceptionHandler();
                }
            }
        }
        return sInstance;
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.i(TAG, "uncaughtException: 线程：" + t.getName() + " \n 奔溃了，异常信息：" + e.toString());
    }
}
