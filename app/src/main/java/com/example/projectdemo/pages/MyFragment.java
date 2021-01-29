package com.example.projectdemo.pages;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.activity.DownRefreshActivity;
import com.example.projectdemo.activity.SeeMoreActivity;
import com.example.projectdemo.mvp.BaseFragment;
import com.example.projectdemo.permission.ActivityPermission;
import com.example.projectdemo.recyclerview.RecyclerActivity;
import com.example.projectdemo.txl.TxlActivity;
import com.example.projectdemo.txl.TxlChangeActivity;
import com.example.projectdemo.update.XUpdate.CustomUpdateParser;
import com.example.projectdemo.update.common.CheckVersion;
import com.example.projectdemo.util.activity.CommonStartActivity;
import com.example.projectdemo.LoginActivity;
import com.example.projectdemo.util.layout.DynaLoadLayout;
import com.example.projectdemo.view.paomadeng.RunHorseLampActivity;
import com.example.projectdemo.view.progressbar.loading.LoadingViewActivity;
import com.xuexiang.xupdate.XUpdate;

public class MyFragment extends BaseFragment implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private View view;
    private String mUpdateUrl = "https://70c99477-5c4c-4335-ad32-d9d6f47cf09d.mock.pstmn.io/server";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        button1 = view.findViewById(R.id.bt_1);
        button2 = view.findViewById(R.id.bt_2);
        button3 = view.findViewById(R.id.bt_3);
        button4 = view.findViewById(R.id.bt_4);
        button5 = view.findViewById(R.id.bt_5);
        button6 = view.findViewById(R.id.bt_6);
        button7 = view.findViewById(R.id.bt_7);
        button8 = view.findViewById(R.id.bt_8);
        button9 = view.findViewById(R.id.bt_9);
        button10 = view.findViewById(R.id.bt_10);
        button11 = view.findViewById(R.id.bt_11);
        button12 = view.findViewById(R.id.bt_12);
        button13 = view.findViewById(R.id.bt_13);
        button14 = view.findViewById(R.id.bt_14);
    }

    private void initEvent() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                CommonStartActivity.actionStart(getActivity(), "张三", 25);
                break;
            case R.id.bt_2:
                DynaLoadLayout.actionStart(getActivity());
                break;
            case R.id.bt_3:
                RecyclerActivity.actionStart(getActivity());
                break;
            case R.id.bt_4:
                LoginActivity.actionStart(getActivity());
                break;
            case R.id.bt_5:
                TxlActivity.actionStart(getActivity());
                break;
            case R.id.bt_6:
                TxlChangeActivity.actionStart(getActivity());
                break;
            case R.id.bt_7:
                CheckVersion checkVersion = new CheckVersion(getActivity());
                new Thread(checkVersion).start();
                break;
            case R.id.bt_8:
                RunHorseLampActivity.actionStart(getActivity());
                break;
            case R.id.bt_9:
                XUpdate.newBuild(getActivity())
                        .updateUrl(mUpdateUrl)
                        .updateParser(new CustomUpdateParser())
                        .update();
                break;
            case R.id.bt_10:
                ActivityPermission.actionStart(getActivity());
                break;
            case R.id.bt_11:
                getWechatApi();
                break;
            case R.id.bt_12:
                SeeMoreActivity.actionStart(getActivity());
                break;
            case R.id.bt_13:
                DownRefreshActivity.actionStart(getActivity());
                break;
            case R.id.bt_14:
                LoadingViewActivity.actionStart(getActivity());
                break;
            default:
                break;
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {

                // 获取权限后需执行的操作

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 获取权限后需执行的操作

                } else {
                    Toast.makeText(getActivity(), "您已拒绝了权限,暂无法获取联系人信息！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到微信
     */
    private void getWechatApi() {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO: handle   exception
            toast("检查到您手机没有安装微信，请安装后使用该功能!");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        checkPermission();
    }
}
