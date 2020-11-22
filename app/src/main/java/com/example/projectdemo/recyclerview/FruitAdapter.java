package com.example.projectdemo.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;

    // 把需要展示的数据源传进来并赋值给mFruitList
    public FruitAdapter(List<Fruit> fruitList) {
        this.mFruitList = fruitList;
    }

    // 获取RecyclerView的子布局中的ImageView和TextView控件实例
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }

    // 创建一个ViewHolder实例，把fruit_item.xml布局加载进来，再传入构造函数，返回一个ViewHolder实例
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // 通过position参数得到当前项Fruit的实例，对RecyclerView子布局的数据进行赋值
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    // 获取RecyclerView中一共有多少个子项
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
