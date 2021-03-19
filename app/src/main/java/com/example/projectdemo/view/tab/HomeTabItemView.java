package com.example.projectdemo.view.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.util.tool.Helper;


/**
 * User: wangjian
 * Date: 2020/10/30
 * Time: 10:07 AM
 */
public class HomeTabItemView extends LinearLayout {
    ImageView mIconView;
    TextView mTextView;

    public HomeTabItemView(Context context) {
        this(context, null);
    }

    public HomeTabItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.item_home_tab, this, true);
        mIconView = findViewById(R.id.tab_icon);
        mTextView = findViewById(R.id.tab_name);
    }

    public void setTextIcon(String text, @DrawableRes int resId) {
        mIconView.setImageResource(resId);
        mTextView.setText(text);
    }

    public void setChoice(boolean choice) {
        Helper.setImageTint(mIconView, choice ? 0xFF007AFF : 0x8000000);
        mTextView.setTextColor(choice ? 0xFF007AFF : 0xFF858E95);
    }
}
