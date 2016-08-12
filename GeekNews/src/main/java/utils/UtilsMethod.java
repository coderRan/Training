package utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 *
 * Created by zdr on 16-8-11.
 */
public class UtilsMethod {

    /**
     * 6.0系统以上的添加读写SD卡的权限
     * @param activity Context
     * @return 是否成功
     */
    public static boolean isGranExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){

            activity.requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },1);

            return false;
        }
        return true;
    }

    /**
     * 根据url返回数据类型
     * @param u = "http://apis.baidu.com/txapi/keji/keji?num=10&page=
     * @return keji
     */
    public static String getUrlName(String u){

        int start = u.lastIndexOf("/");
        int end = u.lastIndexOf("?");
        return u.substring(start+1, end);
    }
}
