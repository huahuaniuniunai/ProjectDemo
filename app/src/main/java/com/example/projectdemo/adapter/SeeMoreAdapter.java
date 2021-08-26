package com.example.projectdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo.R;
import com.example.projectdemo.bean.CityBean;
import com.example.projectdemo.mvp.BaseActivity;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.List;

public class SeeMoreAdapter extends RecyclerView.Adapter<SeeMoreAdapter.ViewHolder> {
    private List<CityBean> mCityList;

    public SeeMoreAdapter(List<CityBean> mCityList) {
        this.mCityList = mCityList;
    }

    @NonNull
    @Override
    public SeeMoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CityBean obj = mCityList.get(position);
                ToastUtils.toast(obj.getName());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeeMoreAdapter.ViewHolder holder, int position) {
        CityBean bean = mCityList.get(position);
        holder.name.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final Button name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.city_name);
        }
    }

    //显示收起时数据
    public void setHideList(List<CityBean> newList) {
        this.mCityList = newList;
        notifyDataSetChanged();
    }

    //显示全部数据
    public void setOpenList(List<CityBean> openList) {
        this.mCityList = openList;
        notifyDataSetChanged();
    }
}
