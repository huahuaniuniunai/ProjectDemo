package com.example.projectdemo.activity.vitamio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.LinearLayout;



public class VitamioActivity extends BaseActivity implements View.OnClickListener, MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener {

    private Button bt_vit1;
    private Button bt_vit2;
    private Button bt_vit3;
    private VideoView mVideoView;
    private ProgressBar pb;
    private FrameLayout fl_controller;
    private TextView downloadRateView;
    private TextView loadRateView;
    private Uri uri;
    boolean isPortrait = false;
    private long mPosition = 0;
    private final String path1 = "rtmp://58.200.131.2:1935/livetv/hunantv";
    private final String path2 = "rtmp://58.200.131.2:1935/livetv/hunantv";
    private final String path3 = "rtmp://58.200.131.2:1935/livetv/hunantv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);
//        Vitamio.initialize(this);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        bt_vit1 = findViewById(R.id.btn_play1);
        bt_vit2 = findViewById(R.id.btn_play2);
        bt_vit3 = findViewById(R.id.btn_play3);
        mVideoView = findViewById(R.id.buffer);
        fl_controller= findViewById(R.id.fl_controller);
//        pb = findViewById(R.id.probar);
//        downloadRateView = findViewById(R.id.download_rate);
//        loadRateView = findViewById(R.id.load_rate);
    }

    private void initEvent() {
        bt_vit1.setOnClickListener(this);
        bt_vit2.setOnClickListener(this);
        bt_vit3.setOnClickListener(this);
    }

    private void initData() {
        initVideo(path1);
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
        Intent intent = new Intent(context, VitamioActivity.class);
        context.startActivity(intent);
    }

    private void initVideo(String path){
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            return;
        } else {
            /*
             * Alternatively,for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLstring));
             */
            uri = Uri.parse(path);
            mVideoView.setVideoURI(uri);
            mVideoView.start();
//            MediaController mc = new MediaController(this);
////            mc.setOnControllerClick(new MediaController.OnControllerClick() {
////                @Override
////                public void OnClick(int type) {
//                    //type 0 全屏。type1 分享
////                    if (type == 0) {
//                        if (isPortrait) {
//                            LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
//                                    getHeightPixel(VitamioActivity.this),
//                                    getWidthPixel(VitamioActivity.this) - getStatusBarHeight(VitamioActivity.this)
//                            );
//
//                            fl_controller.setLayoutParams(fl_lp);
//                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//                            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
//                            isPortrait = false;
//                        } else {
//                            LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
//                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                    DensityUtil.dp2px(VitamioActivity.this, 260)
//                            );
//
//                            fl_controller.setLayoutParams(fl_lp);
//
//                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                            isPortrait = true;
//                        }

//                    }

//                }
//            });
//            mVideoView.setMediaController(mc);
//            mc.setVisibility(View.GONE);
//            //  mVideoView.setMediaController(new MediaController(this));
//            mVideoView.requestFocus();
//            mVideoView.setOnInfoListener(this);
//            mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
//            mVideoView.setOnBufferingUpdateListener(this);
//            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    // optional need Vitamio 4.0
//                    mediaPlayer.setPlaybackSpeed(1.0f);
////                    mVideoView.start();
//                }
//            });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play1:
                initVideo(path1);
                break;
            case R.id.btn_play2:
                initVideo(path2);
                break;
            case R.id.btn_play3:
                initVideo(path3);
                break;
        }
    }
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {

        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(!isPortrait){
                LinearLayout.LayoutParams fl_lp=new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        DensityUtil.dp2px(VitamioActivity.this, 260)
                );
                fl_controller.setLayoutParams(fl_lp);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isPortrait=true;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

    @Override
    protected void onPause() {
        mPosition = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mPosition > 0) {
            mVideoView.seekTo(mPosition);
            mPosition = 0;
        }
        super.onResume();
//        mVideoView.start();


    }


    public  int getHeightPixel(Activity activity)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }
    public  int getWidthPixel(Activity activity)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }
    public  int getStatusBarHeight(Activity activity){
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

}