package com.example.projectdemo.view.treelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dasinwong.treelistview.adapter.TreeAdapter;
import com.dasinwong.treelistview.bean.TreeNode;
import com.dasinwong.treelistview.view.TreeListView;
import com.example.projectdemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeListActivity extends AppCompatActivity {
    private TreeListView mTreeListView;
    private List<TreeNode> treeNodeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);

        mTreeListView = (TreeListView) findViewById(R.id.tree_list_view_redeploy);
        treeNodeList.add(new TreeNode("1", "0", "● 派个人", 0));
        treeNodeList.add(new TreeNode("2", "1", "● 中国电信", 0));
        treeNodeList.add(new TreeNode("3", "2", "● 中国电信湖南分公司", 0));
        treeNodeList.add(new TreeNode("4", "3", "● 长沙市", 0));
        treeNodeList.add(new TreeNode("5", "4", "gc001", 0));
        treeNodeList.add(new TreeNode("6", "4", "gc002", 1));
        treeNodeList.add(new TreeNode("7", "3", "● 岳阳市", 1));
        treeNodeList.add(new TreeNode("8", "7", "gc003", 0));
        treeNodeList.add(new TreeNode("9", "0", "● 派团队/部门", 1));
        treeNodeList.add(new TreeNode("10", "9", "● 中国电信", 0));
        treeNodeList.add(new TreeNode("11", "10", "● 中国电信湖南分公司", 0));
        treeNodeList.add(new TreeNode("12", "11", "长沙市", 0));
        treeNodeList.add(new TreeNode("13", "11", "岳阳市", 1));
        treeNodeList.add(new TreeNode("14", "11", "张家界市", 2));
        Collections.shuffle(treeNodeList);
        mTreeListView.init(treeNodeList, new TreeAdapter.OnNodeClickListener() {
            @Override
            public void onNodeClicked(TreeNode node, String pathContent) {
                if (!TextUtils.isEmpty(getName(pathContent))) {
                    String name = getName(pathContent);
                    Log.d("0523", name);
                    Toast.makeText(TreeListActivity.this, name, Toast.LENGTH_SHORT).show();
                } else {
                    return;
                }
            }
        });
    }

    private String getName(String data) {
        if (!TextUtils.isEmpty(data)) {
            String[] split = data.split("-");
            String content = split[split.length - 1];
            return content;
        }
        return null;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TreeListActivity.class);
        context.startActivity(intent);
    }
}