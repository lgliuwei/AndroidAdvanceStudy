package cn.codingblock.async_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutorService;

import cn.codingblock.thread.R;

public class AsyncTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        MyAsyncTask task1 = new MyAsyncTask(1);
        MyAsyncTask task2 = new MyAsyncTask(2);
        task1.execute();
        task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
