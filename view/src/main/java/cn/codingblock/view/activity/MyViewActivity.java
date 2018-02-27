package cn.codingblock.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.view.R;
import cn.codingblock.view.customer_view.MyView;

public class MyViewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MyViewActivity.class.getSimpleName();

    MyView myview;
    Button btn_handle_myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        myview = ViewUtils.find(this, R.id.myview);
        btn_handle_myview = ViewUtils.find(this, R.id.btn_handle_myview);
        btn_handle_myview.setOnClickListener(this);
        getViewSize("onCreate");

        // 方案二、将任务post到消息队列中，当view初始化完毕后looper会执行任务
        myview.post(new Runnable() {
            @Override
            public void run() {
                getViewSize("post");
            }
        });

        // 方案三、当 View 树的状态或者 View 树内部的 View 的可见性发生改变时，
        // ViewTreeObserver.OnGlobalLayoutListener 接口的 onGlobalLayout() 会被回调，
        // 可以在此方法内部获取 View  的尺寸
        ViewTreeObserver observer = myview.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewSize("onGlobalLayout");
            }
        });

    }

    /**
     * 方案一
     * 当 View 初始化完毕是回调
     * 当 Activity 每次获取和失去焦点时回调
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getViewSize("onWindowFocusChanged");
    }

    private void getViewSize(String methodTag) {
        int width = myview.getWidth();
        int height = myview.getHeight();

        Log.i(TAG, methodTag + ": width=" + width + " | height=" + height);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_handle_myview:
                if (myview.getVisibility() == View.GONE) {
                    myview.setVisibility(View.VISIBLE);
                } else {
                    myview.setVisibility(View.GONE);
                }
                break;
        }
    }
}
