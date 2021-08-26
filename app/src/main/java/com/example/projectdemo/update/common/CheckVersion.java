package com.example.projectdemo.update.common;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.example.projectdemo.application.MyApplication;
import com.example.projectdemo.util.log.LogUtil;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 检查更新
 */
public class CheckVersion implements Runnable{
    //VERSIONINFO_URL是访问服务器拿到查询更新json数据的url,一般这个是在单独的一个数据类中写的。那样的话让CheckVersion继承自那个类。拿到url来用。现在这里设为空是为了方便看，还有以后更改url。
    private static final String VERSIONINFO_URL = "https://70c99477-5c4c-4335-ad32-d9d6f47cf09d.mock.pstmn.io/server";
    private static final String DOWNURL = "http://www.dothantech.com/app/android/SYDY.apk";
    private static final int HAVE_NEW_VERSION = 0;
    private static final int ALREADY_NEW_VERSION = 1;
    private final Context context;
    private UpdateInfo updateInfo;

    public CheckVersion(Context context) {
        this.context = context;
    }

    //获取到主线程的looper,对UI操作
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HAVE_NEW_VERSION:
                    openUpdateDialog();
                    break;
                case ALREADY_NEW_VERSION:
                    Toast.makeText(context, "已经是最新版本", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void openUpdateDialog() {
        LogUtil.d("demo", "来了老弟！");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本有更新");
        builder.setCancelable(false);
        builder.setMessage(updateInfo.data.versionExplain);// 版本描述
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadNewVersion();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void downloadNewVersion() {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("更新进度");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();

        new Thread(new Runnable() {
            InputStream is;
            BufferedInputStream bis;
            FileOutputStream fos;

            @Override
            public void run() {
                //这里完成下载
                try {
                    URL downloadUrl = new URL(updateInfo.data.downloadUrl);// apk下载地址
//                    URL downloadUrl = new URL(DOWNURL);// 测试下载正常
                    HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    int maxlength = connection.getContentLength();
                    pd.setMax(maxlength);

                    is = connection.getInputStream();
                    bis = new BufferedInputStream(is);

                    File file = new File(Environment.getExternalStorageDirectory(), "newversion.apk");
                    fos = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    int len;
                    int loaded = 0;

                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        loaded += len;
                        pd.setProgress(loaded);
                    }
                    installApk(file);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                        bis.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    /**
     * 安装apk
     * @param file
     */
    private void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    @Override
    public void run() {
        try {
            String s = VERSIONINFO_URL;
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            Gson gson = new Gson();
            updateInfo = gson.fromJson(br, UpdateInfo.class);
            //后台版本
            int serverVersionCode = updateInfo.data.version.charAt(0);
            LogUtil.d("demo", "后台版本号：" + serverVersionCode);

            //本地版本获取
            int localVersionCode = getLocalVersionCode();
            LogUtil.d("demo", "本地版本号：" + localVersionCode);

            if ("200".equals(updateInfo.code)) {
                if (serverVersionCode > localVersionCode) {
                    //后台版本新！所以弹框提醒用户有新版本，让用户操作dialog更新
                    Message msg = new Message();
                    msg.what = HAVE_NEW_VERSION;
                    mHandler.sendMessage(msg);

                } else {
                    //后台没有新版本，所以在界面反馈用户不用更新
                    Message msg = new Message();
                    msg.what = ALREADY_NEW_VERSION;
                    mHandler.sendMessage(msg);
                }
            } else {
                Toast.makeText(context, "验证失败！", Toast.LENGTH_SHORT).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getLocalVersionCode() throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
        int versionCode = info.versionCode;
        return versionCode;
    }
}

