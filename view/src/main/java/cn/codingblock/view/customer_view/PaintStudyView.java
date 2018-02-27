package cn.codingblock.view.customer_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.YuvImage;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by liuwei on 17/12/27.
 */
public class PaintStudyView extends View {

    private final static String TAG = PaintStudyView.class.getSimpleName();

    private Paint mTextPaint;  // 绘制文字的Paint
    private Paint mPointPaint; // 绘制参考点的Paint
    private Context mContext;

    private final static float Y_SPACE = 100; // y轴方向的间距

    public PaintStudyView(Context context) {
        super(context);
        init(context);
    }

    public PaintStudyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true); // 消除锯齿
        mTextPaint.setStrokeWidth(1); // 设置笔尖宽度
        mTextPaint.setStyle(Paint.Style.FILL); // 填充
        mTextPaint.setTextSize(30);

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStrokeWidth(3);
        mPointPaint.setColor(Color.RED); // 将参考点的Paint设置为红色
        mPointPaint.setStyle(Paint.Style.STROKE);// 不填充
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String str = "我是一个自定义View的控件";// 待绘制文字

        float x = getWidth() / 2;
        float y = 100;
        canvas.drawPoint(x, y, mPointPaint); // 绘制参考点，便于观察文字处于x，y坐标的位置，从而来学习setTextAlign()方法

        /** 文字绘制 */
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(str, x, y, mTextPaint);

        y += Y_SPACE;
        canvas.drawPoint(x, y, mPointPaint);
        mTextPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(str, 0, 6, x, y, mTextPaint);

        y += Y_SPACE;
        canvas.drawPoint(x, y, mPointPaint);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(str.toCharArray(), 1, 6, x, y, mTextPaint);

        // 1、下开口圆弧方向绘制文字
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        y += Y_SPACE;
        Path path = new Path();
        path.addArc(new RectF(x - 150, y, x + 150, y + 300), 180,180);
        canvas.drawPath(path, mPointPaint); // 参考弧度线
        canvas.drawTextOnPath(str, path, 0, 0, mTextPaint); // 按照path路径绘制文字，不偏移
        canvas.drawTextOnPath(str, path, 30, 30, mTextPaint);// 向水平、垂直方向各偏移30
        canvas.drawTextOnPath(str, path, 60, 60, mTextPaint);// 向水平、垂直方向各偏移60

        // 2、上开口圆弧方向绘制文字
        path.reset();
        y += Y_SPACE;
        path.addArc(new RectF(x - 150, y, x + 150, y + 300), 0, 180);
        canvas.drawPath(path, mPointPaint); // 参考弧度线
        canvas.drawTextOnPath(str, path, 0, 0, mTextPaint);
        canvas.drawTextOnPath(str, path, 30, 30, mTextPaint);
        canvas.drawTextOnPath(str, path, 60, 60, mTextPaint);
        path.close();

        // 3、竖直方向绘制文字
        path.reset();
        path.moveTo(200, y);
        path.lineTo(200, y + 4 * Y_SPACE);
        canvas.drawPath(path, mPointPaint); // 参考弧度线
        canvas.drawTextOnPath(str, path, 0, 0, mTextPaint);
        canvas.drawTextOnPath(str, path, 30, 60, mTextPaint);


        y += Y_SPACE;
        y += Y_SPACE;
        y += Y_SPACE;
        y += Y_SPACE;

        // 4、水平方向绘制文字
        path.reset();
        path.moveTo(x, y);
        path.lineTo(x + 4 * Y_SPACE, y);
        canvas.drawPath(path, mPointPaint); // 参考弧度线
        canvas.drawTextOnPath(str, path, 0, 0, mTextPaint);
        canvas.drawTextOnPath(str, path, 30, 60, mTextPaint);

        y += Y_SPACE;
        // isRtl: 是否倒序
        canvas.drawTextRun(str, 1, 4, 1, 8, x, y, true, mTextPaint);  // API 23 添加


        /** Paint的使用 */

        // 基线和文字的关系
        y += Y_SPACE;
        canvas.drawPoint(x, y, mPointPaint);
        canvas.drawLine(x - 300, y, x+300, y, mPointPaint);
        mTextPaint.setTextAlign(Paint.Align.CENTER);// 水平方向上让文字居中
        float ascent = mTextPaint.ascent(); // 根据文字大小获取文字顶端到文字基线的距离（返回的是负值）
        float descent = mTextPaint.descent(); // 根据文字大小获取文字底部到文字基线的距离（返回的事正值）
        canvas.drawLine(x - 300, y + ascent, x+300, y + ascent, mPointPaint);
        canvas.drawLine(x - 300, y + descent, x+300, y + descent, mPointPaint);
        Log.i(TAG, "onDraw: ascent=" + ascent + " | descent=" + descent);
        canvas.drawText(str, x, y, mTextPaint);

        // 将文字的中心定位在参考点上
        y += Y_SPACE;
        canvas.drawPoint(x, y, mPointPaint);
        canvas.drawText(str, x, y - ascent / 2 - descent / 2, mTextPaint);

        /* Shader 渐变 */
        y = 100;
        Shader shader = new LinearGradient(x - 50, y - 80, x + 50, y + 80,
                Color.parseColor("#FFCCBB"), Color.parseColor("#FF0000"), Shader.TileMode.CLAMP);
        mTextPaint.setShader(shader);
        canvas.drawRect(x - 500, y - 80, x + 500, y + 80, mTextPaint);

        y += 3 * Y_SPACE;
        Shader shader1 = new LinearGradient(x - 50, y - 80, x + 50, y + 80,
                Color.parseColor("#FFCCBB"), Color.parseColor("#FF0000"), Shader.TileMode.REPEAT);
        mTextPaint.setShader(shader1);
        canvas.drawRect(x - 500, y - 80, x + 500, y + 80, mTextPaint);

        y += 3 * Y_SPACE;
        Shader shader2 = new LinearGradient(x - 50, y - 80, x + 50, y + 80,
                Color.parseColor("#FFCCBB"), Color.parseColor("#FF0000"), Shader.TileMode.MIRROR);
        mTextPaint.setShader(shader2);
        canvas.drawRect(x - 500, y - 80, x + 500, y + 80, mTextPaint);

    }
}
