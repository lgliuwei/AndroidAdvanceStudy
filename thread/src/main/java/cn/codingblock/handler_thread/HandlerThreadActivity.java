package cn.codingblock.handler_thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.thread.R;

public class HandlerThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = HandlerThreadActivity.class.getSimpleName();
    private static final int MSG_HANDLER_THREAD = 1000;
    private static final int MSG_HANDLER1_INFO = 1001;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private Handler mThread1Handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        ViewUtils.findAndOnClick(this, R.id.btn_start_thread, this);
        ViewUtils.findAndOnClick(this, R.id.btn_handler_thread_send, this);

        mHandlerThread = new HandlerThread("handler_thread");
        mHandlerThread.start();

        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_HANDLER_THREAD:
                        Log.i(TAG, "handleMessage: MSG_HANDLER_THREAD");

                        // 在接收到在主线程中通过mHandler发来的消息后，开启一段耗时任务，而此任务可以与主线程中的任务同时异步执行，说明了此任务跑在了handler_thread线程中

                        try {
                            int n = 10;
                            while (n-- > 0) {
                                Log.i(TAG, "run: " + Thread.currentThread());
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_thread:

                new Thread1().start();
                new Thread2().start();
                break;
            case R.id.btn_handler_thread_send:
                mHandler.sendEmptyMessage(MSG_HANDLER_THREAD);
                try {
                    int n = 10;
                    while (n-- > 0) {
                        Log.i(TAG, "run: " + Thread.currentThread());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }

    class Thread1 extends Thread {
        String TAG = Thread1.class.getSimpleName();

        @Override
        public void run() {
            super.run();
            // 而普通的Thread想要通过Handler接受消息，必须在线程内部使用Looper.prepare()和Looper.loop()
            Looper.prepare();

            mThread1Handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.i(TAG, "handleMessage: " + msg.what + Thread.currentThread());

                    try {
                        while (true) {
                            Log.i(TAG, "run: ");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Looper.loop();

        }
    }

    class Thread2 extends Thread {
        String TAG = Thread2.class.getSimpleName();

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(3000);
                mThread1Handler.sendEmptyMessage(MSG_HANDLER1_INFO);

                while (true) {
                    Log.i(TAG, "run: ");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
