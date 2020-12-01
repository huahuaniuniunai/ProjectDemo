package com.example.projectdemo.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectdemo.R;
import com.example.projectdemo.view.paomadeng.RunHorseLampActivity;

public class ActivityPermission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        cameraCheck();
        albumCheck();
    }

    /**
     * 相机权限检查
     */
    private void cameraCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(ActivityPermission.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ActivityPermission.this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
            } else {
                // 获取权限后需执行的操作
            }
        }
    }

    /**
     * 相册检查权限
     */
    private void albumCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(ActivityPermission.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ActivityPermission.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2);
            } else {

                // 获取权限后需执行的操作

            }
        }
    }

    /**
     * 权限选择回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 获取权限后需执行的操作

                } else {
                    Toast.makeText(this, "很遗憾！您拒绝了访问相机权限。", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 获取权限后需执行的操作

                } else {
                    Toast.makeText(this, "很遗憾！您拒绝了访问相册权限。", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ActivityPermission.class);
        context.startActivity(intent);
    }
}