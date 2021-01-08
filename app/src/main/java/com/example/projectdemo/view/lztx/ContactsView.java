package com.example.projectdemo.view.lztx;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo.R;
import com.example.projectdemo.view.lztx.util.CharacterParser;
import com.example.projectdemo.view.lztx.util.PinyinComparator;
import com.example.projectdemo.view.lztx.util.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ContactsView extends FrameLayout implements SideBarView.LetterTouchListener {
    private LinearLayout ll_top_title;
    private TextView tv_letter;
    private SideBarView sideBarView;
    private RecyclerView recycler_View;
    private TextView tv_letter_show;
    private int mLetterHeight = 0;
    private int mCurrentPosition = 0;
    private LinearLayoutManager mLayoutManager = null;
    private List<SortModel> mData = new ArrayList<>();
    private CharacterParser mParser = CharacterParser.getInstance();

    public ContactsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.contact_list_layout, this);
        ll_top_title = findViewById(R.id.ll_top_title);
        tv_letter = findViewById(R.id.tv_letter);
        tv_letter_show = findViewById(R.id.tv_letter_show);
        sideBarView = findViewById(R.id.view_sidebar);
        sideBarView.setLetterTouchListener(this);
        recycler_View = findViewById(R.id.sort_recycler_view);
        recycler_View.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mLetterHeight = ll_top_title.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                View view = mLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null && view.getTop() <= mLetterHeight && view.findViewById(R.id.tv_letter).getVisibility() == View.VISIBLE) {
                    ll_top_title.setY((float) (-(mLetterHeight - view.getTop())));
                } else {
                    ll_top_title.setY(0f);
                }

                if (mCurrentPosition != mLayoutManager.findFirstVisibleItemPosition()) {
                    ll_top_title.setY(0f);
                    updateLetter();
                }
            }
        });
    }

    public RecyclerView getRecycler() {
        return recycler_View;
    }

    @Override
    public void setLetterVisibility(int visibility) {
        tv_letter_show.setVisibility(visibility);
    }

    @Override
    public void setLetter(String letter) {
        if (TextUtils.isEmpty(letter) || letter.isEmpty()) {
            return;
        }
        tv_letter_show.setText(letter);
        int position = getPositionForSection((int)((letter.toCharArray())[0]));
        if (position != -1) {
            updateLetter();
            mLetterHeight  = ll_top_title.getHeight();
            if (mLayoutManager != null) {
                mLayoutManager.scrollToPositionWithOffset(position, 0);
            } else {
                mLayoutManager = null;
            }
        }
    }

    private void updateLetter(){
        if (mLayoutManager == null) {
            mCurrentPosition = -1;
        } else {
            mCurrentPosition = mLayoutManager.findFirstVisibleItemPosition();
        }
        if (mData.size() > 0 && mCurrentPosition > -1 && mCurrentPosition < mData.size()){
            tv_letter.setText(mData.get(mCurrentPosition).getLetter());
        }
    }

    public List<SortModel> sortData(List<String> data) {
        List<SortModel> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            SortModel sm = new SortModel();
            sm.setName(data.get(i));
            String pinyin = mParser.getSelling(data.get(i));
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sm.setLetter(sortString);
            } else {
                sm.setLetter("#");
            }
            list.add(sm);
        }
        Collections.sort(list, new PinyinComparator());
        return list;
    }

    public List<SortModel> sortDataByName(List<SortModel> contact) {
        List<SortModel> list = new ArrayList<>();
        for (int i = 0; i < contact.size(); i++) {
            SortModel sm = new SortModel();
            sm.setName(contact.get(i).getName());
            sm.setTelPhone(contact.get(i).getTelPhone());
            String pinyin = mParser.getSelling(contact.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sm.setLetter(sortString);
            } else {
                sm.setLetter("#");
            }
            list.add(sm);
        }
        Collections.sort(list, new PinyinComparator());
        return list;
    }

    public void initData(List<SortModel> data) {
        mData.clear();
        if (data == null || data.size() == 0) {
            mData.addAll(new ArrayList<>());
        } else {
            mData.addAll(data);
        }
        updateLetter();
    }

    public List<SortModel> updateData(String filterStr) {
        List<SortModel> newData = new ArrayList<>();
        if (mData != null && mData.size() > 0) {
            if ("" == filterStr) {
                newData = mData;
            } else {
                for (SortModel sortModel : mData) {
                    String name = sortModel.getName();
                    if (name.indexOf(filterStr) != -1 || mParser.getSelling(name).startsWith(filterStr)) {
                        newData.add(sortModel);
                    }
                }
            }
        }
        mData.clear();
        mData.addAll(newData);
        updateLetter();
        return mData;
    }

    private int getPositionForSection(int section) {
        if (mData == null || mData.size() == 0) {
            return  -1;
        }
        for (int i = 0; i < mData.size(); i++) {
            String s = mData.get(i).getLetter();
            char firstChar = (s.toUpperCase().toCharArray())[0];
            if ((int)firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
