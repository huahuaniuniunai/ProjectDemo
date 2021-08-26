package com.example.projectdemo.view.cardviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollapsbleToolbarActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;
    private final Fruit[] fruits = {new Fruit("Apple", R.drawable.apple_pic)
            , new Fruit("Banana", R.drawable.banana_pic)
            , new Fruit("Orange", R.drawable.orange_pic)
            , new Fruit("Watermelon", R.drawable.watermelon_pic)
            , new Fruit("Pear", R.drawable.pear_pic)
            , new Fruit("Grape", R.drawable.grape_pic)
            , new Fruit("Pineapple", R.drawable.pineapple_pic)
            , new Fruit("Strawberry", R.drawable.strawberry_pic)
            , new Fruit("Cheery", R.drawable.cherry_pic)
            , new Fruit("Mango", R.drawable.mango_pic)};

    List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsble_toolbar);

        initFruits();
        RecyclerView recyclerView = findViewById(R.id.card_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = findViewById(R.id.card_swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {// 切换回主线程
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);// 隐藏刷新进度条
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CollapsbleToolbarActivity.class);
        context.startActivity(intent);
    }
}