package com.example.projectdemo.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectdemo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        // 初始化数据
        initFruits();
        // 获取RecyclerView的实例
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        // LayoutManager用于指定LayoutManager的布局方式，LinearLayoutManager是线性布局，实现和ListView类似的效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 默认垂直排列
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置为水平排列
        recyclerView.setLayoutManager(layoutManager);
        // 创建FruitAdapter的实例，并将数据源传入FruitAdapter构造函数中获取实例
        FruitAdapter adapter = new FruitAdapter(fruitList);
        // 适配器设置，把数据和RecyclerView关联起来
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(R.drawable.apple_pic, "apple");
            fruitList.add(apple);

            Fruit banana = new Fruit(R.drawable.banana_pic, "banana");
            fruitList.add(banana);

            Fruit orange = new Fruit(R.drawable.orange_pic, "orange");
            fruitList.add(orange);

            Fruit watermelon = new Fruit(R.drawable.watermelon_pic, "watermelon");
            fruitList.add(watermelon);

            Fruit pear = new Fruit(R.drawable.pear_pic, "pear");
            fruitList.add(pear);

            Fruit grape = new Fruit(R.drawable.grape_pic, "grape");
            fruitList.add(grape);

            Fruit pineapple = new Fruit(R.drawable.pineapple_pic, "pineapple");
            fruitList.add(pineapple);

            Fruit strawberry = new Fruit(R.drawable.strawberry_pic, "strawberry");
            fruitList.add(strawberry);

            Fruit cherry = new Fruit(R.drawable.cherry_pic, "cherry");
            fruitList.add(cherry);

            Fruit mango = new Fruit(R.drawable.mango_pic, "mango");
            fruitList.add(mango);
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecyclerActivity.class);
        context.startActivity(intent);
    }
}
