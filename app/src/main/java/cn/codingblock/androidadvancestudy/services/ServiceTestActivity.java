package cn.codingblock.androidadvancestudy.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.libutils.view.ViewUtils;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = ServiceTestActivity.class.getSimpleName();
    
    Context context;
    MyService.MyBinder myBinder;
    Intent intent;
    private Button btn_start_service;
    private Button btn_bind_service;
    private Button btn_unbind_service;
    private Button btn_stop_service;
    private Button btn_service_stopself;
    private Button btn_start_servicetest2;
    private Button btn_start_foreground_service;
    private Button btn_start_intent_service;
    private Button btn_start_2_service;
    private Button btn_start_main_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        Log.i(TAG, "onCreate: 线程：" + Thread.currentThread());
        context = getApplicationContext();
        btn_start_service = ViewUtils.find(this, R.id.btn_start_service);
        btn_bind_service = ViewUtils.find(this, R.id.btn_bind_service);
        btn_unbind_service = ViewUtils.find(this, R.id.btn_unbind_service);
        btn_stop_service = ViewUtils.find(this, R.id.btn_stop_service);
        btn_service_stopself = ViewUtils.find(this, R.id.btn_service_stopself);
        btn_start_servicetest2 = ViewUtils.find(this, R.id.btn_start_servicetest2);
        btn_start_foreground_service = ViewUtils.find(this, R.id.btn_start_foreground_service);
        btn_start_intent_service = ViewUtils.find(this, R.id.btn_start_intent_service);
        btn_start_2_service = ViewUtils.find(this, R.id.btn_start_2_service);
        btn_start_main_task = ViewUtils.find(this, R.id.btn_start_main_task);
        btn_start_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
        btn_service_stopself.setOnClickListener(this);
        btn_start_servicetest2.setOnClickListener(this);
        btn_start_foreground_service.setOnClickListener(this);
        btn_start_intent_service.setOnClickListener(this);
        btn_start_2_service.setOnClickListener(this);
        btn_start_main_task.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                startService(new Intent(context, MyService.class));
                break;
            case R.id.btn_bind_service:
                bindService(new Intent(context, MyService.class),serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                try {
                    unbindService(serviceConnection);
                } catch (Exception e) {
                    Log.e(TAG, "onClick: 解绑Service出异常了！");
                    e.printStackTrace();
                }
            case R.id.btn_stop_service:
                stopService(new Intent(context, MyService.class));
                break;
            case R.id.btn_service_stopself:
                if (myBinder != null) {
                    myBinder.stopSelf();
                } else {
                    Log.i(TAG, "onClick: myBinder = null");
                }
                break;
            case R.id.btn_start_servicetest2:
                startActivity(new Intent(ServiceTestActivity.this, ServiceTest2Activity.class));
                break;
            case R.id.btn_start_foreground_service:
                startService(new Intent(context, ForegroundService.class));
                break;
            case R.id.btn_start_intent_service:
                startService(new Intent(context, MyIntentService.class));
                break;
            case R.id.btn_start_2_service:
                // 同时启动 MyService 、MyIntentService 并观察log
                startService(new Intent(context, MyService.class));
                startService(new Intent(context, MyIntentService.class));
                break;
            case R.id.btn_start_main_task:
                // 等 MyService 、MyIntentService 启动完毕之后再开启 mainTask 并观察log
                mainTask();
                break;
        }
    }

    private void mainTask() {
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

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MyService.MyBinder)iBinder;
            Log.i(TAG, "onServiceConnected: componentName = " + componentName + " | iBinder = " + iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myBinder = null;
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };
}
