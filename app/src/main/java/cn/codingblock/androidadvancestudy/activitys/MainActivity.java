package cn.codingblock.androidadvancestudy.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.androidadvancestudy.broadcast.BroadcastTestActivity;
import cn.codingblock.androidadvancestudy.contentprovides.ProviderTestActivity;
import cn.codingblock.androidadvancestudy.services.ServiceTestActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    String TAG = MainActivity.class.getSimpleName();

    private Button btn_start_single_task;
    private Button btn_start_single_instance;
    private Button btn_start_bactviity;
    private Button btn_start_service_test;
    private Button btn_start_broadcast;
    private Button btn_start_provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        btn_start_single_task = (Button) findViewById(R.id.btn_start_single_task);
        btn_start_single_instance = (Button) findViewById(R.id.btn_start_single_instance);
        btn_start_service_test = (Button) findViewById(R.id.btn_start_service_test);
        btn_start_bactviity = (Button) findViewById(R.id.btn_start_bactviity);
        btn_start_broadcast = (Button) findViewById(R.id.btn_start_broadcast);
        btn_start_provider = (Button) findViewById(R.id.btn_start_provider);
        btn_start_single_task.setOnClickListener(this);
        btn_start_single_instance.setOnClickListener(this);
        btn_start_service_test.setOnClickListener(this);
        btn_start_bactviity.setOnClickListener(this);
        btn_start_broadcast.setOnClickListener(this);
        btn_start_provider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_single_task:
                startActivity(new Intent(MainActivity.this, SingleTaskActivity.class));
                break;
            case R.id.btn_start_single_instance:
                startActivity(new Intent(MainActivity.this, SingleInstanceActivity.class));
                break;
            case R.id.btn_start_service_test:
                startActivity(new Intent(MainActivity.this, ServiceTestActivity.class));
                break;
            case R.id.btn_start_bactviity:
                startActivity(new Intent(MainActivity.this, BActivity.class));
                finish();
                break;
            case R.id.btn_start_broadcast:
                startActivity(new Intent(MainActivity.this, BroadcastTestActivity.class));
                break;
            case R.id.btn_start_provider:
                startActivity(new Intent(MainActivity.this, ProviderTestActivity.class));
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("info", "activity state");
        Log.i(TAG, "onSaveInstanceState: Bundle=" + outState.get("info"));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: Bundle=" + savedInstanceState.get("info"));
    }
}
