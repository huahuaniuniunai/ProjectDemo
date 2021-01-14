package com.example.projectdemo.update.XUpdate;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projectdemo.application.MyApplication;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.listener.IUpdateParseCallback;
import com.xuexiang.xupdate.proxy.IUpdateParser;
import com.xuexiang.xutil.net.JsonUtil;

/**
 * 自定义更新解析器
 */
public class CustomUpdateParser implements IUpdateParser {
    @Override
    public UpdateEntity parseJson(String json) throws Exception {
        return getParseResult(json);
    }

    private UpdateEntity getParseResult(String json) {
        CustomResult result = JsonUtil.fromJson(json, CustomResult.class);
        if (result != null) {
            if ("200".equals(result.code)) {
                return new UpdateEntity()
                        .setHasUpdate(true)// 是否有更新
//                        .setIsIgnorable(true)// 是否可忽略
                        .setVersionName(result.data.version)
                        .setUpdateContent(result.data.versionExplain)
                        .setDownloadUrl(result.data.downloadUrl)
                        .setSize(result.data.apkSize);
            } else {
                Toast.makeText(MyApplication.getContext(), "验证失败！", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    @Override
    public void parseJson(String json, @NonNull IUpdateParseCallback callback) throws Exception {
        //当isAsyncParser为 true时调用该方法, 所以当isAsyncParser为false可以不实现
        callback.onParseResult(getParseResult(json));
    }


    @Override
    public boolean isAsyncParser() {
        return false;
    }
}
