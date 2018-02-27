package cn.codingblock.view.customer_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.Random;

import cn.codingblock.view.R;

/**
 * 自定义 View 简单示例
 * Created by liuwei on 17/12/14.
 */
public class MyView extends View {

    private final static String TAG = MyView.class.getSimpleName();

    private Paint mPaint = new Paint();
    private int mColor = Color.parseColor("#ff0000");

    private int mPaddingTop;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;

    private int mUsableWidth; // 可用宽度（减去padding后的宽度）
    private int mUsableHeight;// 可用高度（减去padding后的高度）

    private int mUsableStartX = 0; // 画笔起始点的x坐标
    private int mUsableStartY = 0; // 画笔其实点的y坐标

    private int mCircleX; // 圆心x坐标
    private int mCircleY; // 圆心y坐标
    private int mCircleRadius;// 圆的半径

    private Random mRandom = new Random(100);

    private int[] mColors = new int[] {
      Color.parseColor("#ff0000"),
      Color.parseColor("#ffffff"),
      Color.parseColor("#ff00ff"),
      Color.parseColor("#ffff00"),
      Color.parseColor("#ff00ff"),
      Color.parseColor("#0000ff")
    };

    private Context mContext;
    private ScaleGestureDetector mScaleGestureDetector; // 缩放手势检测
    private float mScaleRate = 1; // 缩放比率

    /**
     * 在代码中通过new MyView()加载控件时会调用此构造方法
     * @param context
     */
    public MyView(Context context) {
        super(context);
        Log.i(TAG, "MyView(Context context)：");
        mContext = context;
        init();
    }

    /**
     * 在xml中使用控件时会调用此构造方法
     * 在xml中使用控件并且还有自定义属性时也是调用此构造方法
     * @param context
     * @param attrs
     */
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "MyView(Context context, @Nullable AttributeSet attrs): ");
        mContext = context;
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mColor = typeArray.getColor(R.styleable.MyView_circle_color, mColor);
        typeArray.recycle();
        init();
    }

    private void init() {
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setColor(mColor); // 为画笔设置颜色
        // 初始化 ScaleGestureDetector 并添加缩放手势监听器
        mScaleGestureDetector = new ScaleGestureDetector(mContext, mOnScaleGestureListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 重写此方法，对自定义控件在 wrap_content 情况下设置默认宽、高
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heithtSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(500, 500);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(500, heithtSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 500);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout: left=" + left + " | top=" + top + " | right=" + right + " | bottom=" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();

        // 可用宽度和宽度要考虑padding
        mUsableWidth = getWidth() - mPaddingRight - mPaddingLeft;
        mUsableHeight = getHeight() - mPaddingTop - mPaddingBottom;
        // 画笔起始点要考虑padding
        mUsableStartX = mPaddingLeft;
        mUsableStartY = mPaddingTop;

        // 确定可用区域的中心为圆心
        mCircleX = mUsableStartX + mUsableWidth / 2;
        mCircleY = mUsableStartY + mUsableHeight / 2;

        // 确定圆的半径，以可用宽度和高度两者较短的一半为圆的半径
        if (mUsableWidth <= mUsableHeight) {
            mCircleRadius = mUsableWidth / 2;
        } else {
            mCircleRadius = mUsableHeight / 2;
        }

        // 让半径乘以缩放倍率
        mCircleRadius *= mScaleRate;

        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mColor = mColors[mRandom.nextInt(6)];
                mPaint.setColor(mColor);
                invalidate(); // 通知控件重绘
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: ACTION_MOVE");
                break;
        }

        // 让缩放手势检测器接管触摸事件
        if (mScaleGestureDetector.onTouchEvent(event)) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    private ScaleGestureDetector.OnScaleGestureListener mOnScaleGestureListener = new ScaleGestureDetector.OnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.i(TAG, "onScale: " + detector.getScaleFactor());
            // 获取缩放比例因子并累乘到缩放倍率上
            mScaleRate *= detector.getScaleFactor();
            postInvalidate();
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Log.i(TAG, "onScaleBegin: " + detector.getScaleFactor());
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            Log.i(TAG, "onScaleEnd: " + detector.getScaleFactor());
        }
    };


    /**
     * 当所在的 Activity 被启动时会调用此方法
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow: ");
    }

    /**
     * 当 View 所在的 Activity 退出时，或者 View 被 remove 时，此方法会被调用
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow: ");
        /**
         * 可在此做一些收尾工作
         */
    }

}
