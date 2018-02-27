package cn.codingblock.libutils.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by liuwei on 18/2/9.
 */

public class PermissionManager {

    /**
     * 检查是否拥有使用权
     * @param context
     * @param permission
     * @return
     */
    public static boolean isGranted(Context context, String permission) {
        return !isMarshmallow() || isGranted_(context, permission);
    }

    /**
     * 判断是否Android 6.0以上
     * @return
     */
    private static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 是否申请了该使用权限
     * @param context
     * @param permission
     * @return
     */
    private static boolean isGranted_(Context context, String permission) {
        int checkSelfPermission = ActivityCompat.checkSelfPermission(context.getApplicationContext(), permission);
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED;
    }
}
