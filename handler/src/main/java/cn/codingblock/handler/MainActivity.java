package cn.codingblock.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    final static String TAG = MainActivity.class.getSimpleName();
    final static int MSG_TEST = 1;

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TEST:
                    Log.i(TAG, "handleMessage: MSG_TEST | " + Thread.currentThread());
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private MyHandler mHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHander = new MyHandler();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i(TAG, "run: sendMsg: MSG_TEST | " + Thread.currentThread());
            }
        }).start();

        try {
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mHander.sendEmptyMessage(MSG_TEST);

        ThreadLocal<String> str = new ThreadLocal<>();
        str.set("123");
    }
}
