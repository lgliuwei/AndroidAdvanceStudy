package cn.codingblock.androidadvancestudy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 静态注册的广播接收器6
 * Created by liuwei on 17/12/7.
 */
public class MyBroadcast6Receiver extends BroadcastReceiver {

    String TAG = MyBroadcast6Receiver.class.getSimpleName();

    public static final String ACTION = "MY_BROADCAST_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "接收到广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));
//        abortBroadcast();
//        Log.i(TAG, "丢弃广播");
        setResult(006, "我是老6传来的消息", null);
    }
}
