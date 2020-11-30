package com.example.projectdemo.util.media;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.projectdemo.R;

/**
 * 设置手机铃声
 */
public class MediaUtils {

    private static AssetManager assetManager;
    private static MediaPlayer player = null;
    public static boolean mStop = true;
    public static MediaPlayer playRing(Context context) {
        Log.e("IOExceptionaa","IOException  33");
        player = MediaPlayer.create(context, R.raw.call_ring);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Log.e("aaaaaaa","ddddddddd "+System.currentTimeMillis());

                if(mStop){
                   // player.reset();
                    player.start();
                }
            }
        });



     /*   try {
            player = new MediaPlayer();
             assetManager = context.getAssets();
           *//* AssetFileDescriptor fileDescriptor = assetManager.openFd("call_ring.mp3");
            player.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),
                    fileDescriptor.getStartOffset());
            player.prepareAsync();*//*
            player=MediaPlayer.create(context, R.raw.call_ring);
            player.start();
            MediaPlayer finalPlayer = player;
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.e("IOExceptionaa","IOException  11");

                }
            });

            Log.e("IOExceptionaa","IOException  22");

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOExceptionaa","IOException  "+e.getMessage());
        }*/
        return player;
    }


    // 停止媒体
    public static void stopVideo(){
        if(player!=null){
            synchronized (MediaUtils.class){
                if(player!=null&&player.isPlaying()){
                    player.stop();
                    player.release();
                    player=null;
                }
            }
        }
    }

}
