package com.example.projectdemo.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.example.projectdemo.R;
import com.example.projectdemo.view.paomadeng.RunHorseLampActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityPermission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initPermission();
//        cameraCheck();
    }

    /**
     * 相机权限检查
     */
    /*private void cameraCheck() {
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
    }*/

    /**
     * 权限选择回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 获取权限后需执行的操作

                } else {
                    Toast.makeText(this, "很遗憾！您拒绝了访问相机权限。", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }*/

    /**
     * 批量申请运行时权限
     */
    //1、首先声明一个数组permissions，将所有需要申请的权限都放在里面
    String[] permissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET};
    //2、创建一个mPermissionList，逐个判断哪些权限未授权，将未授权的权限存储到mPermissionList中
    List<String> mPermissionList = new ArrayList<>();
    //3、声明一个请求码，在请求权限的回调方法onRequestPermissionsResult中需要判断使用
    private final int mRequestCode = 100;//权限请求码
    //4、权限判断和申请
    private void initPermission(){
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0;i<permissions.length;i++){
            if (ContextCompat.checkSelfPermission(this,permissions[i])!=
                    PackageManager.PERMISSION_GRANTED){
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size()>0){//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this,permissions,mRequestCode);
        }else {
            //权限已经都通过了，可以将程序继续打开了
        }
    }
    /**
     * 5.请求权限后回调的方法
     * @param requestCode 是我们自己定义的权限请求码
     * @param permissions 是我们请求的权限名称数组
     * @param grantResults 是我们在弹出页面后是否允许权限的标识数组，数组的长度对应的是权限
     *                     名称数组的长度，数组的数据0表示允许权限，-1表示我们点击了禁止权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode==requestCode){
            for (int i=0;i<grantResults.length;i++){
                if (grantResults[i]==-1){
                    hasPermissionDismiss=true;
                    break;
                }
            }
        }
        if (hasPermissionDismiss){//如果有没有被允许的权限
            showPermissionDialog();
        }else {
            //权限已经都通过了，可以将程序继续打开了
        }
    }
    /**
     *  6.不再提示权限时的展示对话框
     */
    AlertDialog mPermissionDialog;
    String mPackName = "com.example.projectdemo";

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + mPackName);
                            Intent intent = new Intent(Settings.
                                    ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            ActivityPermission.this.finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }
    //7、关闭对话框
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ActivityPermission.class);
        context.startActivity(intent);
    }
}