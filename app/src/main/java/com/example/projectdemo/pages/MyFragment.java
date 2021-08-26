package com.example.projectdemo.pages;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.activity.AnimatorActivity;
import com.example.projectdemo.activity.DownRefreshActivity;
import com.example.projectdemo.activity.SeeMoreActivity;
import com.example.projectdemo.activity.TimeActivity;
import com.example.projectdemo.broadcast.DynBroadcastActivity;
import com.example.projectdemo.callback.CallbackFunction;
import com.example.projectdemo.mvp.BaseActivity;
import com.example.projectdemo.util.dialog.AlertDialogRemark;
import com.example.projectdemo.util.preference.PreferenceUtil;
import com.example.projectdemo.view.cardviewdemo.CollapsbleToolbarActivity;
import com.example.projectdemo.activity.ijkplayer.IjkplayerActivity;
import com.example.projectdemo.activity.vitamio.VitamioActivity;
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
import com.example.projectdemo.view.gridviewdemo.GridViewActivity;
import com.example.projectdemo.view.paomadeng.RunHorseLampActivity;
import com.example.projectdemo.view.progressbar.loading.LoadingViewActivity;
import com.example.projectdemo.view.treelist.TreeListActivity;
import com.example.sdk.SdkDemoActivity;
import com.xuexiang.xupdate.XUpdate;

public class MyFragment extends BaseFragment implements View.OnClickListener {
    private EditText et_search;
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
    private Button button15;
    private Button button16;
    private Button button17;
    private Button button18;
    private Button button19;
    private Button button20;
    private Button button21;
    private Button button22;
    private Button button23;
    private Button button24;
    private Button button25;

    private View view;
    private final String mUpdateUrl = "https://70c99477-5c4c-4335-ad32-d9d6f47cf09d.mock.pstmn.io/server";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        /**
         * 1.在Activity中使用：ButterKnife.bind(this);建议在BaseActivity中完成绑定。
         * 2.在Fragment中使用：ButterKnife.bind(this, view); 在onDestroyView回调中调用它的unbind方法进行Fragment解绑。
         * 3.在ViewHolder中使用：ButterKnife.bind(this, view);在ViewHolder中加一个含参数view的构造方法，并在构造方法中绑定。
         */
//        ButterKnife.bind(this, view);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        et_search = view.findViewById(R.id.search);
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
        button15 = view.findViewById(R.id.bt_15);
        button16 = view.findViewById(R.id.bt_16);
        button17 = view.findViewById(R.id.bt_17);
        button18 = view.findViewById(R.id.bt_18);
        button19 = view.findViewById(R.id.bt_19);
        button20 = view.findViewById(R.id.bt_20);
        button21 = view.findViewById(R.id.bt_21);
        button22 = view.findViewById(R.id.bt_22);
        button23 = view.findViewById(R.id.bt_23);
        button24 = view.findViewById(R.id.bt_24);
        button25 = view.findViewById(R.id.bt_25);
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
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);
        button17.setOnClickListener(this);
        button18.setOnClickListener(this);
        button19.setOnClickListener(this);
        button20.setOnClickListener(this);
        button21.setOnClickListener(this);
        button22.setOnClickListener(this);
        button23.setOnClickListener(this);
        button24.setOnClickListener(this);
        button25.setOnClickListener(this);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String key = v.getText().toString().trim();// 获取输入框内容
                    // TODO
                    return true;
                }
                return false;
            }
        });
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
            case R.id.bt_15:
                Intent intent = new Intent(getActivity(), SdkDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_16:
                IjkplayerActivity.actionStart(getActivity());
                break;
            case R.id.bt_17:
                VitamioActivity.actionStart(getActivity());
                break;
            case R.id.bt_18:
                TimeActivity.actionStart(getActivity());
                break;
            case R.id.bt_19:
                // TODO
                break;
            case R.id.bt_20:
                CollapsbleToolbarActivity.actionStart(getActivity());
                break;
            case R.id.bt_21:
                GridViewActivity.actionStart(getActivity());
                break;
            case R.id.bt_22:
                TreeListActivity.actionStart(getActivity());
                break;
            case R.id.bt_23:
                AnimatorActivity.actionStart(getActivity());
                break;
            case R.id.bt_24:
                DynBroadcastActivity.actionStart(getActivity());
                break;
            case R.id.bt_25:
                showProgressDialog("模拟网络延迟...");
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                        new AlertDialogRemark(getActivity(), new CallbackFunction() {
                            @Override
                            public void onStart() {

                            }
                            @Override
                            public void onSuccess() {
                                // 获取已存储本地输入框内容
                                String processDesc = PreferenceUtil.get().getCacheString("processDesc", "");
                                toast("获取输入框的内容："+processDesc);
                            }
                            @Override
                            public void onFailed(String msg) {
                                // 获取失败
                                toast("获取失败！");
                            }
                        }).show();// 弹框
                    }
                },3000); // 延时3秒
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

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.bind(this, view).unbind();
//    }
}
