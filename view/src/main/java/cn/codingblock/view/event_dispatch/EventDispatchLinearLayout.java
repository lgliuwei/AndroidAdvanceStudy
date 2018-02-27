package cn.codingblock.view.event_dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 事件分发机制测试 ViewGroup
 * Created by liuwei on 18/1/5.
 */
public class EventDispatchLinearLayout extends LinearLayout {

    private final static String TAG = "——Layout";//EventDispatchLinearLayout.class.getSimpleName();


    public EventDispatchLinearLayout(Context context) {
        super(context);
    }

    public EventDispatchLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: " + event.getAction() + " | 分发事件");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TAG, "onInterceptTouchEvent: " + event.getAction() + " | 是否拦截:" + false);
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event.getAction() + " | 是否消费事件:" + false);
        return super.onTouchEvent(event);
    }
}
