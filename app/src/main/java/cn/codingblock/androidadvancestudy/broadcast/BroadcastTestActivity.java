package cn.codingblock.androidadvancestudy.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.libutils.view.ViewUtils;

/**
 *
 */
public class BroadcastTestActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTENT_INFO = "INTENT_INFO";
    Button btn_send_broadcast;
    Button btn_send_ordered_broadcast;
    Button btn_send_local_broadcast;
    Button btn_send_sticky_broadcast;
    Intent intent;
    Context context;
    DynamicBroadcastReceiver dynamicBroadcastReceiver;
    int n = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);
        context = getApplicationContext();
        btn_send_broadcast = ViewUtils.find(this, R.id.btn_send_broadcast);
        btn_send_ordered_broadcast = ViewUtils.find(this, R.id.btn_send_ordered_broadcast);
        btn_send_local_broadcast = ViewUtils.find(this, R.id.btn_send_local_broadcast);
        btn_send_sticky_broadcast = ViewUtils.find(this, R.id.btn_send_sticky_broadcast);
        btn_send_broadcast.setOnClickListener(this);
        btn_send_ordered_broadcast.setOnClickListener(this);
        btn_send_local_broadcast.setOnClickListener(this);
        btn_send_sticky_broadcast.setOnClickListener(this);
        intent = new Intent(MyBroadcastReceiver.ACTION);
        // 动态注册广播接收器
        dynamicBroadcastReceiver = new DynamicBroadcastReceiver();
        registerReceiver(dynamicBroadcastReceiver, new IntentFilter(MyBroadcastReceiver.ACTION));

        LocalBroadcastManager.getInstance(context).registerReceiver(new MyBroadcastReceiver(), new IntentFilter(MyBroadcastReceiver.ACTION));
        LocalBroadcastManager.getInstance(context).registerReceiver(new MyBroadcast2Receiver(), new IntentFilter(MyBroadcastReceiver.ACTION));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_broadcast:
                intent.putExtra(INTENT_INFO, "我是一个普通广播");
                sendBroadcast(intent);
                break;

            case R.id.btn_send_ordered_broadcast:
                intent.putExtra(INTENT_INFO, "我是一个有序广播");
                sendOrderedBroadcast(intent, null);
                break;

            case R.id.btn_send_local_broadcast:
                intent.putExtra(INTENT_INFO, "我是一个本地广播");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                break;

            case R.id.btn_send_sticky_broadcast:
                // sticky广播已经被弃用了
                intent.putExtra(INTENT_INFO, "我是sticky广播" + n++);
                sendStickyBroadcast(intent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynamicBroadcastReceiver);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(new MyBroadcastReceiver());
        LocalBroadcastManager.getInstance(context).unregisterReceiver(new MyBroadcast2Receiver());
    }
}
