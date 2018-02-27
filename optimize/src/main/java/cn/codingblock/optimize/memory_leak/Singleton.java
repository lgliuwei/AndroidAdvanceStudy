package cn.codingblock.optimize.memory_leak;

import android.content.Context;

/**
 * Created by liuwei on 17/12/22.
 */

public class Singleton {
    private static Singleton sInstance;
    private Context mContext;

    private Singleton(Context context) {
        mContext = context;
    }

    public static Singleton getInstance(Context context) {
        if (sInstance == null) {
            synchronized (Singleton.class) {
                if (sInstance == null) {
                    // 在传入context是，应使用 context.getApplicationContext() 而避免直接使用传进来的 context 本身，
                    // 这是因为 context 是Activity对象，直接传入单例中让单例一直持有Activity的对象，导致Activity对象不能释放，造成内存泄漏。
                    // 而 context.getApplicationContext() 获取的则是 App 的 Application 对象。
                    sInstance = new Singleton(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
