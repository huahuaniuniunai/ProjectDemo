package com.example.projectdemo.update.XUpdate;

import androidx.annotation.NonNull;

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
            return new UpdateEntity()
                    .setHasUpdate(result.hasUpdate)
                    .setIsIgnorable(result.isIgnorable)
                    .setVersionCode(result.versionCode)
                    .setVersionName(result.versionName)
                    .setUpdateContent(result.updateLog)
                    .setDownloadUrl(result.apkUrl)
                    .setSize(result.apkSize);
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
