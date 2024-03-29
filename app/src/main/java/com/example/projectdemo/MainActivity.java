package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectdemo.mvp.BaseActivity;
import com.example.projectdemo.pages.ContactsFragment;
import com.example.projectdemo.pages.DynamicFragment;
import com.example.projectdemo.pages.MessageFragment;
import com.example.projectdemo.pages.MyFragment;
import com.example.projectdemo.util.activity.ActivityManager;
import com.example.projectdemo.view.tab.HomeTabItemView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends BaseActivity {
    private final String[] mTabsNameArray = new String[4];
    private final int[] mTabIconArray = new int[4];
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private PageAdapter mPageAdapter;
    private long mExitTime;       //实现“再按一次退出”的记录时间变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager2 = findViewById(R.id.view_page);
        mTabLayout = findViewById(R.id.tab_layout);
        initViewPage();
        initTabLayout();
        checkPermission();
    }

//    @Override
//    protected void setStatusBar() {
//        StatusBarHelper.setStatusBar(this,
//                StatusBarHelper.VZStatusBarType.BAR_TYPE_COLOR,
//                Color.parseColor("#DBDBDD"),// 设置状态栏颜色
//                StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA, true);// 设置状态栏字体是深色，flase亮色
//    }

    @Override
    protected void setBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.gray)// 设置状态栏颜色
                .statusBarDarkFont(true)// 状态栏字体是深色，不写默认为亮色
                .fitsSystemWindows(true)// 设置解决状态栏和布局重叠问题
                .navigationBarColor(R.color.gray)// 设置导航栏颜色
                .init();
    }

    private void initViewPage() {
        mViewPager2.setOffscreenPageLimit(4);
        mViewPager2.setUserInputEnabled(false);
        mPageAdapter = new PageAdapter();
        mViewPager2.setAdapter(mPageAdapter);
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0, len = mTabLayout.getTabCount(); i < len; i++) {
                    TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
                    if (itemTab != null) {
                        HomeTabItemView itemTabView = (HomeTabItemView) itemTab.getCustomView();
                        if (itemTabView != null) {
                            itemTabView.setChoice(i == position);
                        }
                    }
                }
            }
        });
    }

    private void initTabLayout() {
        mTabsNameArray[0] = getResources().getString(R.string.home_tab_message);
        mTabsNameArray[1] = getResources().getString(R.string.home_tab_contacts);
        mTabsNameArray[2] = getResources().getString(R.string.home_tab_find);
        mTabsNameArray[3] = getResources().getString(R.string.home_tab_mine);
        mTabIconArray[0] = R.drawable.message_icon_home;
        mTabIconArray[1] = R.drawable.contacts_icon_home;
        mTabIconArray[2] = R.drawable.find_icon_home;
        mTabIconArray[3] = R.drawable.mine_icon_home;
        TabLayoutMediator mediator = new TabLayoutMediator(mTabLayout, mViewPager2, true, false, (tab, position) -> {
            HomeTabItemView tabItemView = new HomeTabItemView(getActivity());
            tabItemView.setTextIcon(mTabsNameArray[position], mTabIconArray[position]);
            tab.setCustomView(tabItemView);
        });
        mediator.attach();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
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
                    Toast.makeText(this, "很遗憾！您拒绝了存储权限。", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private class PageAdapter extends FragmentStateAdapter {
        public PageAdapter() {
            super(getSupportFragmentManager(), getLifecycle());
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 1:
                    return new ContactsFragment();
                case 2:
                    return new DynamicFragment();
                case 3:
                    return new MyFragment();
                default:
                    return new MessageFragment();
            }
        }

        @Override
        public int getItemCount() {
            return mTabsNameArray.length;
        }
    }

    @Override //再按一次退出程序
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime < 2000) {
            super.onBackPressed();
            ActivityManager.finishAll();
        } else {
            mExitTime = System.currentTimeMillis();
            toast("再按一次退出");
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
