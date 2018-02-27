package cn.codingblock.androidadvancestudy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by liuwei on 17/12/8.
 */

public class DynamicBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = DynamicBroadcastReceiver.class.getSimpleName();

    public static final String ACTION = TAG;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "接收到广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));
    }
}
