package cn.codingblock.androidadvancestudy.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.androidadvancestudy.activitys.MainActivity;

/**
 * 前台Service
 */
public class ForegroundService extends Service {

    String TAG = ForegroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 线程：" + Thread.currentThread());
        showNotification();
    }

    private void showNotification() {

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是ContentTitle")
                .setContentText("我是ContentText");
        // 创建通知被点击时出发的Intent
        Intent intent = new Intent(this, MainActivity.class);
        // 创建任务栈
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 构建通知
        final Notification notification = builder.build();
        // 显示通知
        notificationManager.notify(0, notification);
        // 启动为前台服务
        startForeground(0, notification);

        // 开启一个
        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        builder.setContentText("更新" + n++);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
