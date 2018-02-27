package cn.codingblock.view.event_dispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.view.R;

/**
 * 事件分发机制测试Activity
 * Created by liuwei on 18/1/5.
 */
public class EventDispatchActivity extends AppCompatActivity {

    private final static String TAG = "Activity";//EventDispatchActivity.class.getSimpleName();

    private EventDispatchTestView edtv_test;
    private EventDispatchLinearLayout edll_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        edtv_test = ViewUtils.find(this, R.id.edtv_test);
        edll_test = ViewUtils.find(this, R.id.edll_test);
        
        edtv_test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("————View", "onTouch: 返回 " + true);
                return true;
            }
        });

        edtv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("————View", "onClick: ");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        // 被调用时输出log，event.getAction表示事件的类型，0：ACTION_DOWN，1：ACTION_UP，2：ACTION_MOVE。

        Log.i(TAG, "dispatchTouchEvent: " + event.getAction() + " | 分发事件");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event.getAction() + " | 是否消费事件:" + true);
        return super.onTouchEvent(event);
    }
}
