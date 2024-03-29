package com.example.projectdemo.util.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

/**
 * Android23后需要对明感权限进行使用前的校验
 */

public class PermissionUtil {
    /**
     * 权限校验
     *
     * @param activity
     */
    public static void checkPermission(Activity activity, String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            int requestCode = 0;
            ArrayList<String> permissionsInfo = new ArrayList<String>();

            for (String item :
                    permissions) {
                int check = activity.checkSelfPermission(item);

                if (check != PackageManager.PERMISSION_GRANTED) {
                    requestCode |= 1 << 0;
                    permissionsInfo.add(item);
                }

            }
            if (requestCode > 0) {
                String[] permission = new String[permissionsInfo.size()];
                activity.requestPermissions(permissionsInfo.toArray(permission), requestCode);
            }
        }
    }
}

/**
 * 调用方式
 */
/*
    PermissionUtil.checkPermission(MainActivity.this,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE);
*/
