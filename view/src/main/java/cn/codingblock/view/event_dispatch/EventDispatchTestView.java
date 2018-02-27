package cn.codingblock.view.event_dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 事件分发机制测试 View
 * Created by liuwei on 18/1/5.
 */
public class EventDispatchTestView extends Button {

    private final static String TAG = "————View";//EventDistpatchTestView.class.getSimpleName();

    public EventDispatchTestView(Context context) {
        super(context);
    }

    public EventDispatchTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: " + event.getAction() + " | 分发事件");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event.getAction() + " | 是否消费事件:" + true);
        return super.onTouchEvent(event);
    }
}
