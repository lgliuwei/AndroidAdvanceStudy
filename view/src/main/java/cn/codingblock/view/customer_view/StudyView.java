package cn.codingblock.view.customer_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuwei on 17/12/26.
 */

public class StudyView extends View {

    private Paint mPaint;
    private Context mContext;

    public StudyView(Context context) {
        super(context);
        init(context);
    }

    public StudyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStrokeWidth(5); // 设置笔尖宽度
        mPaint.setStyle(Paint.Style.STROKE); // 不填充
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /** 1、drawArc */

        // 绘制扇形
        canvas.drawArc(0, 0, 200, 200, 0, 90, true, mPaint);
        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawArc(rectF, 180, 90, true, mPaint);

        // 绘制圆弧
        canvas.drawArc(300, 0, 500, 200, 0, 90, false, mPaint);
        RectF rectF1 = new RectF(300, 0, 500, 200);
        canvas.drawArc(rectF1, 180, 90, false, mPaint);

        /** 2、drawBitmap */
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), android.R.mipmap.sym_def_app_icon);
        // 绘制图片
        canvas.drawBitmap(bitmap, 0, 300, null);
        // 将图片拉伸平铺在RectF矩形内
        canvas.drawBitmap(bitmap, null, new RectF(200, 300, 500, 500), null);
        // 截取图片的四分之一拉伸平铺在RectF矩形内
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth()/2, bitmap.getHeight()/2), new RectF(500, 300, 800, 500), null);

        Matrix matrix = new Matrix();
        matrix.postTranslate(800, 300); // 将bitmap平移到此位置
        canvas.drawBitmap(bitmap, matrix, mPaint);

        // 为防止oom，及时回收bitmap
        bitmap.recycle();

        /** 3、drawCircle */
        canvas.drawCircle(100, 700, 100, mPaint);

        /** 4、绘制一个点 */
        canvas.drawPoint(100, 700, mPaint); // 绘制一个点

        float[] points = new float[] {
            130, 700,
            160, 700,
            190, 700,
            210, 700,
            240, 700
        };

        canvas.drawPoints(points, 2, 4, mPaint); // 绘制一组点（代表跳过前两个值，处理4个值，也就是实际绘制2个点）


        RectF rectF2 = new RectF(300, 600, 700, 800); // 创建一个RectF

        /** 5、drawOval 绘制椭圆 */
        canvas.drawOval(rectF2, mPaint);

        /** 6、drawRect 绘制矩形*/
        canvas.drawRect(rectF2, mPaint);
        canvas.drawRoundRect(rectF2, 60, 30, mPaint);

        /** 7、drawLine */
        canvas.drawLine(100, 820, 800, 820, mPaint);

        float[] lines = new float[]{
                100f, 850f, 800f, 850f,
                100f, 900f, 800f, 900f,
                100f, 950f, 800f, 950f
        };
        canvas.drawLines(lines, mPaint); // 按floats数组中，四个数为1组，绘制多条线


        /** 8、drawPath */

        // 使用 Path 绘制一个楼梯
        Path path = new Path();
        path.moveTo(0, 1000);
        path.lineTo(100, 1000);
        path.lineTo(100, 1100);
        path.lineTo(200, 1100);
        path.lineTo(200, 1200);
        path.lineTo(300, 1200);
        path.lineTo(300, 1300);
        path.lineTo(400, 1300);
        path.lineTo(400, 1400);
        path.close();
        canvas.drawPath(path, mPaint);

        // 使用 Path 绘制一个Android机器人

        // 绘制两个触角
        path.reset();
        path.moveTo(625, 1050);
        path.lineTo(650, 1120);
        path.moveTo(775, 1050);
        path.lineTo(750, 1120);

        path.addArc(new RectF(600, 1100, 800, 1300), 180, 180); // 绘制头部
        path.addCircle(666.66f, 1150, 10, Path.Direction.CW); // 绘制眼睛，CW：顺时针绘制， CCW：逆时针绘制
        path.addCircle(733.33f, 1150, 10, Path.Direction.CW);
        path.addRect(new RectF(600, 1200, 800, 1300), Path.Direction.CW);  // 身体
        canvas.drawPath(path, mPaint);
    }
}
