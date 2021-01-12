package com.example.projectdemo.lztx.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo.R;
import com.example.projectdemo.lztx.activity.PersonalDetailsActivity;
import com.example.projectdemo.lztx.db.SortModel;

import java.util.List;


/**
 * User: zhangshiwu
 * Date: 2020/11/05
 * Time: 11:00 PM
 */
public class ContactSortAdapter extends RecyclerView.Adapter<ContactSortAdapter.ContactHolder> {
    protected List<SortModel> mData = null;

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_sort, null);
        ContactHolder holder = new ContactHolder(view);
        holder.txlName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SortModel sortModel = mData.get(position);
                String name = sortModel.getName();
                String tel = sortModel.getTelPhone();
                PersonalDetailsActivity.actionStart(parent.getContext(), name, tel);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        SortModel sortModel = mData.get(position);
        holder.txlName.setText(sortModel.getName());
        holder.txlTel.setText(sortModel.getTelPhone());
        if (!compareSection(position)) {
            holder.tv_letter.setVisibility(View.VISIBLE);
            holder.tv_letter.setText(sortModel.getLetter());
            holder.line_view.setVisibility(View.GONE);
        } else {
            holder.tv_letter.setVisibility(View.GONE);
            holder.line_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }

    public void initData(List<SortModel> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    private Boolean compareSection(int position) {
        return (position == 0) ? false : (getSectionForPosition(position) == getSectionForPosition(position - 1));
    }

    private int getSectionForPosition(int position) {
        return (int)(mData.get(position).getLetter().toCharArray())[0];
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < mData.size(); i++) {
            String s = mData.get(i).getLetter();
            char firstChar = (s.toUpperCase().toCharArray())[0];
            if ((int)firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        public TextView txlName;
        public TextView txlTel;
        public TextView tv_letter;
        public View line_view;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            txlName = itemView.findViewById(R.id.adapter_name);
            txlTel = itemView.findViewById(R.id.adapter_tel);
            tv_letter = itemView.findViewById(R.id.tv_letter);
            line_view = itemView.findViewById(R.id.line_view);
        }
    }
}