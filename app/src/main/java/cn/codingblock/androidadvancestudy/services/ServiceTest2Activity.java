package cn.codingblock.androidadvancestudy.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.libutils.view.ViewUtils;

public class ServiceTest2Activity extends AppCompatActivity implements View.OnClickListener {
    String TAG = ServiceTest2Activity.class.getSimpleName();
    
    Context context;
    MyService.MyBinder myBinder;
    private Button btn_start_service;
    private Button btn_bind_service;
    private Button btn_unbind_service;
    private Button btn_stop_service;
    private Button btn_service_stopself;
    private Button btn_start_servicetest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        context = getApplicationContext();
        btn_start_service = ViewUtils.find(this, R.id.btn_start_service);
        btn_bind_service = ViewUtils.find(this, R.id.btn_bind_service);
        btn_unbind_service = ViewUtils.find(this, R.id.btn_unbind_service);
        btn_stop_service = ViewUtils.find(this, R.id.btn_stop_service);
        btn_service_stopself = ViewUtils.find(this, R.id.btn_service_stopself);
        btn_start_servicetest2 = ViewUtils.find(this, R.id.btn_start_servicetest2);
        btn_start_servicetest2.setVisibility(View.GONE);
        btn_start_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
        btn_service_stopself.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                startService(new Intent(context, MyService.class));
                break;
            case R.id.btn_bind_service:
                /**
                 * 第三个参数：
                 * BIND_AUTO_CREATE：在绑定Service时，如果Service还没有被创建，会自动创建Service
                 *
                 */
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
            // 只有Service被外界异常终止了才会调用此方法，unBindService时不会调用此方法
            myBinder = null;
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unbindService(serviceConnection);
        } catch (Exception e) {
            Log.e(TAG, "解绑Service出异常了！");
            e.printStackTrace();
        }
    }
}
