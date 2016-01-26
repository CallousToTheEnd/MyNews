package com.example.li.fragmenttabhosttest.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Mr.li on 2016/1/26.
 */
public class Player implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

    private Context mContext;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHoder;
    public MediaPlayer player;

    public Player(Context mContext, SurfaceView mSurfaceView) {
        super();
        this.mContext = mContext;
        this.mSurfaceView = mSurfaceView;
        // 给SurfaceView添加CallBack监听
        mSurfaceHoder = mSurfaceView.getHolder();
        mSurfaceHoder.addCallback(this);
        // 为了可以播放视频或者使用Camera预览，我们需要指定其Buffer类型
        mSurfaceHoder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
        player.setOnInfoListener(this);
        player.setOnPreparedListener(this);
        player.setOnSeekCompleteListener(this);
        player.setOnVideoSizeChangedListener(this);
        // 然后指定需要播放文件的路径，初始化MediaPlayer
        String dataPath = Environment.getExternalStorageDirectory().getPath()
                + "/biz_guide_video.mp4";
        try {
            player.setDataSource(dataPath);
            Log.v("Next:::", "surfaceDestroyed called");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        player.start();
    }

    public void pause() {
        player.pause();
    }

    public void stop() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 当prepare完成后，该方法触发，在这里我们播放视频
        //player.start();
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        // 当一些特定信息出现或者警告时触发
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                break;
            case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                break;
            case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                break;
            case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                break;
        }
        return false;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.v("Play Error:::", "MEDIA_ERROR_SERVER_DIED");
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.v("Play Error:::", "MEDIA_ERROR_UNKNOWN");
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 当SurfaceView中的Surface被创建的时候被调用
        // 在这里我们指定MediaPlayer在当前的Surface中进行播放
        player.setDisplay(holder);
        // 在指定了MediaPlayer播放的容器后，我们就可以使用prepare或者prepareAsync来准备播放了
        player.prepareAsync();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }


}
