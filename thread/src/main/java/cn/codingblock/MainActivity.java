package cn.codingblock;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.codingblock.async_task.AsyncTaskActivity;
import cn.codingblock.handler_thread.HandlerThreadActivity;
import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.thread.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.findAndOnClick(this, R.id.btn_async_task, this);
        ViewUtils.findAndOnClick(this, R.id.btn_handler_thread, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_async_task:
                startActivity(new Intent(getApplicationContext(), AsyncTaskActivity.class));
                break;
            case R.id.btn_handler_thread:
                startActivity(new Intent(getApplicationContext(), HandlerThreadActivity.class));
                break;
        }
    }
}
