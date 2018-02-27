package cn.codingblock.androidadvancestudy.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    private Binder binder;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 线程：" + Thread.currentThread());
        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 10;
                while (n-- > 0) {
                    try {
                        Thread.sleep(1000);
                        Log.i(TAG, "我是一个耗时任务，执行剩余时间：" + n);
                        Thread.yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public MyBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public void stopSelf() {
            Log.i(TAG, "MyBinder.stopSelf: 被调用了");
            MyService.this.stopSelf();
        }
    }
}
