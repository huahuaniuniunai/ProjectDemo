package com.example.projectdemo.activity.ijkplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.projectdemo.R;
import com.example.projectdemo.activity.ijkplayer.media.IRenderView;
import com.example.projectdemo.activity.ijkplayer.media.IjkVideoView;
import com.example.projectdemo.activity.ijkplayer.media.PlayerManager;
import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class IjkplayerActivity extends BaseActivity implements View.OnClickListener {
    private IjkVideoView mVideoView;
    private PlayerManager player;
    private Button bt_ijk1;
    private Button bt_ijk2;
    private Button bt_ijk3;

    private String path1 = "rtmp://58.200.131.2:1935/livetv/hunantv";
    private String path2 = "rtmp://58.200.131.2:1935/livetv/hunantv";
    private String path3 = "rtmp://58.200.131.2:1935/livetv/hunantv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkplayer);

        initView();
//        initEvent();
        startNormal(path1);

//        startHorizontal();
    }

    private void initView() {
        mVideoView = findViewById(R.id.video_view);
        bt_ijk1 = findViewById(R.id.btn_ijk1);
        bt_ijk2 = findViewById(R.id.btn_ijk2);
        bt_ijk3 = findViewById(R.id.btn_ijk3);
    }

    private void initEvent() {
        bt_ijk1.setOnClickListener(this);
        bt_ijk2.setOnClickListener(this);
        bt_ijk3.setOnClickListener(this);
    }

    /** 普通播放 start **/
    private void startNormal(String url) {
        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.start();
    }

    /** 调用startHorizontal()方法滑动控制的话解开注释 **/
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (player.gestureDetector.onTouchEvent(event))
//            return true;
//        return super.onTouchEvent(event);
//    }

    /**
     * 可左半屏滑动控制亮度  右半屏控制音量  双击切换比例  （无提示）
     */
    private void startHorizontal(String url) {
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.live(true);
        player.setScaleType(PlayerManager.SCALETYPE_WRAPCONTENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(new PlayerManager.PlayerStateListener() {
            @Override
            public void onComplete() {
                Log.e("   player  status    :", "complete");
            }

            @Override
            public void onError() {
                Log.e("   player  status    :", "error");
            }

            @Override
            public void onLoading() {
                Log.e("   player  status    :", "loading");
            }

            @Override
            public void onPlay() {
                Log.e("   player  status    :", "play");
            }
        });
        player.play(url);
        IjkVideoView videoView = player.getVideoView();
        videoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                switch (i) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        break;
                }
                return false;

            }
        });
    }

    @Override
    protected void setBar() {
        ImmersionBar.with(this)
                .titleBar(R.id.tb_login)
                .keyboardEnable(true)
                .navigationBarColor(R.color.page)
                .init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, IjkplayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ijk1:
                startNormal(path1);
                break;
            case R.id.btn_ijk2:
                startNormal(path2);
                break;
            case R.id.btn_ijk3:
                startNormal(path3);
                break;
            default:
                break;
        }
    }
}