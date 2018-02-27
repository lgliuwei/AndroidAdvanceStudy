package cn.codingblock.androidadvancestudy.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {
    
    String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 这里非主线程，可在这里做一些耗时任务
        Log.i(TAG, "onHandleIntent: 线程：" + Thread.currentThread());
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
}
