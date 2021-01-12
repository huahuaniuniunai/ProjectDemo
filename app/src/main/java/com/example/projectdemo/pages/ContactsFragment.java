package com.example.projectdemo.pages;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo.R;
import com.example.projectdemo.application.MyApplication;
import com.example.projectdemo.lztx.activity.NewPeopleActivity;
import com.example.projectdemo.lztx.adapter.ContactSortAdapter;
import com.example.projectdemo.lztx.db.DatabaseUtil;
import com.example.projectdemo.lztx.db.SortModel;
import com.example.projectdemo.lztx.util.RecyclerViewUtil;
import com.example.projectdemo.lztx.view.ContactsView;
import com.example.projectdemo.lztx.view.SideBarView;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends ImmersionFragment implements SideBarView.LetterTouchListener, View.OnClickListener {
    private View view;
    private EditText mTxlSearch;
    private TextView tv_letter_show;
    private RecyclerView recyclerView;
    private ContactsView mContactsView;
    private ContactSortAdapter mAdapter = null;
    private List<SortModel> mDataList = new ArrayList<>();

    public ContactsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {
        mTxlSearch = view.findViewById(R.id.txl_search);
        mContactsView = view.findViewById(R.id.contact_view);
    }

    private void initData() {
        // 拿到数据库中所有的对象，包含姓名和号码
        List<SortModel> contact = DatabaseUtil.getContact();
//        for (SortModel sm : contact) {
//            Log.d("refresh", sm.getName() + "-" + sm.getTelPhone());
//        }

        mAdapter = new ContactSortAdapter();
        RecyclerViewUtil.initNoDecoration(getActivity(), mContactsView.getRecycler(), mAdapter);
        mDataList = mContactsView.sortDataByName(contact);
        mContactsView.initData(mDataList);
        mAdapter.initData(mDataList);
    }

    private void initEvent() {
        mTxlSearch.setOnClickListener(this);
        mTxlSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s.toString().trim())) {
                    mContactsView.initData(mDataList);
                    mAdapter.initData(mDataList);
                } else {
                    mAdapter.initData(mContactsView.updateData(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 检查权限
     */
    private void check() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                boolean first = isFirstStart(MyApplication.getContext());
                if (first) {
                    readContacts();
                }
                initView();
                initEvent();
                initData();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean first = isFirstStart(MyApplication.getContext());
                    if (first) {
                        readContacts();
                    }
                    initView();
                    initEvent();
                    initData();
                } else {
                    Toast.makeText(getActivity(), "您已拒绝了权限,暂无法获取联系人信息！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取系统联系人及存储联系人姓名、号码到TxlDatabase.db数据库
     */
    public void readContacts() {
        Cursor cursor = null;
        List<String> tel = new ArrayList<>();
        for (int i = 0; i < DatabaseUtil.getContactAllTel().size(); i++) {
            tel.add(DatabaseUtil.getContactAllTel().get(i).getTelPhone());
        }
        try {
            cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String telephone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    SortModel sortModel = new SortModel();
                    // 联系人信息存储到数据库表SortModel中
                    if (!tel.contains(telephone)) {
                        sortModel.setName(name);
                        sortModel.setTelPhone(telephone);
                        tel.add(telephone);
                        sortModel.save();
                    }

                    // 加载系统联系人姓名到通讯录
//                    data.add(name);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void refresh() {
        List<SortModel> contact = DatabaseUtil.getContact();
        mDataList = mContactsView.sortDataByName(contact);
        mContactsView.initData(mDataList);
        mAdapter.initData(mDataList);
    }

    public boolean isFirstStart(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("NB_FIRST_START", 0);
        boolean isFirst = preferences.getBoolean("FIRST_START", true);
        if (isFirst) {
            preferences.edit().putBoolean("FIRST_START", false).commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(view)
                .statusBarColor(R.color.gray)
                .navigationBarColor(R.color.gray)
                .init();
    }

    @Override
    public void setLetterVisibility(int visibility) {
        tv_letter_show = view.findViewById(R.id.tv_letter_show);
        tv_letter_show.setVisibility(visibility);
    }

    @Override
    public void setLetter(String letter) {
        if (mAdapter == null || letter == null) {
            return;
        }
        tv_letter_show.setText(letter);
        int position = mAdapter.getPositionForSection((int)((letter.toCharArray())[0]));
        if (position != -1) {
            recyclerView = view.findViewById(R.id.sort_recycler_view);
            ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        check();
        refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txl_new_people:
                NewPeopleActivity.actionStart(getActivity());
                break;
//            case R.id.refresh:
//                readContacts();
//                initData();
//                break;
            default:
                break;
        }
    }
}
