package cn.codingblock.view.conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by liuwei on 18/1/23.
 */

public class ListViewFroScrollView extends ListView {

    public ListViewFroScrollView(Context context) {
        super(context);
    }

    public ListViewFroScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 当ListView被ScrollView嵌套时，ListView使用的测量模式是ScrollView传入的MeasureSpec.UNSPECIFIED。
         * 而使用UNSPECIFIED这种模式时只能加载一部分，所以这里使用我们自己构建的heightMeasureSpec，向makeMeasureSpec传入一个比较大的值
         * 和at_most模式
         */
        int newHightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHightMeasureSpec);
    }
}
