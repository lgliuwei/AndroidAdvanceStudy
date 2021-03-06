package cn.codingblock.androidadvancestudy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 静态注册的广播接收器
 * Created by liuwei on 17/12/7.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    String TAG = MyBroadcastReceiver.class.getSimpleName();

    public static final String ACTION = "MY_BROADCAST_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "接收到广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));
    }
}
