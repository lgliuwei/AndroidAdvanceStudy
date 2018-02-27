package cn.codingblock.libutils.view;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by liuwei on 17/12/4.
 */

public class ViewUtils {
    public static <T extends View>T find(Activity activity, @IdRes int view) {
        return (T) activity.findViewById(view);
    }

    public static <T extends View>T findAndOnClick(Activity activity, @IdRes int view, View.OnClickListener listener) {
        View viewTag = activity.findViewById(view);
        viewTag.setOnClickListener(listener);
        return (T) viewTag;
    }
}
