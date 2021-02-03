package com.example.projectdemo.activity.ijkplayer.core_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projectdemo.R;
import com.example.projectdemo.activity.ijkplayer.IjkplayerActivity;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class CoreModuleActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    private String url = "rtmp://58.200.131.2:1935/livetv/hunantv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_module);
        mVideoView = findViewById(R.id.video_view);
        //初始化
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        //监听
        mVideoView.setListener(new IjkVideoView.VideoPlayerListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                //播放成功处理
                mp.start();
            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.i("PlayerListener", "onCompletion");
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                Log.i("PlayerListener", "onError");
                Toast.makeText(CoreModuleActivity.this, "播放失败！", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //路径
        mVideoView.setPath(url);
        mVideoView.start();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, IjkplayerActivity.class);
        context.startActivity(intent);
    }
}