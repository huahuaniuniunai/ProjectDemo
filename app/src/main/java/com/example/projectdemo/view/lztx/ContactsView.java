package com.example.projectdemo.view.lztx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;

class ContactsView extends FrameLayout {
    public ContactsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.contact_list_layout, this);
    }
}
