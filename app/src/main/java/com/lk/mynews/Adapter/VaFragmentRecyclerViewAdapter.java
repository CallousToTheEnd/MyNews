package com.lk.mynews.Adapter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.lk.mynews.Bean.VaFragmentItemBean;
import com.lk.mynews.R;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/1/26.
 */
public class VaFragmentRecyclerViewAdapter extends BaseRecyclerViewAdapter implements View.OnClickListener {

    private List<VaFragmentItemBean> mVideos = new ArrayList<>();
    private Context mContext;

    private MyViewHolder viewHolder;

    VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {

        }
    });

    public VaFragmentRecyclerViewAdapter(List<VaFragmentItemBean> mVideos, Context mContext) {
        this.mVideos = mVideos;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.va_recyclerview_viewholder, parent, false);
        viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder mvh = (MyViewHolder) holder;
        final String dataPath = Environment.getExternalStorageDirectory().getPath()
                + "/test.mp4";
        mvh.getVvVideo().addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener() {

            @Override
            public void onVideoPreparedMainThread() {
                mvh.getBtnListPlay().setVisibility(View.INVISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {
                mvh.getBtnListPlay().setVisibility(View.VISIBLE);
            }

            @Override
            public void onVideoStoppedMainThread() {
                mvh.getBtnListPlay().setVisibility(View.VISIBLE);
            }
        });
        mvh.getVvVideo().setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mVideoPlayerManager.stopAnyPlayback();
                mvh.getBtnListPlay().setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        mvh.getBtnListPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mVideoPlayerManager.playNewVideo(null, mvh.getVvVideo(), dataPath);
            }
        });

        mvh.getTvTitle().setText(mVideos.get(position).getTitle());
        mvh.getTvDesc().setText(mVideos.get(position).getDesc());
        if (mVideos.get(position).getDuration() < 60) {
            mvh.getTvDuration().setText("00:" + mVideos.get(position).getDuration());
        } else if (mVideos.get(position).getDuration() >= 60) {
            String m = String.valueOf(mVideos.get(position).getDuration() / 60);
            String s = String.valueOf(mVideos.get(position).getDuration() % 60);
            if (Integer.parseInt(m) < 10) {
                m = "0" + m;
            }
            if (Integer.parseInt(s) < 10) {
                s = "0" + s;
            }
            mvh.getTvDuration().setText(m + ":" + s);
        }
        if (mVideos.get(position).getTie() < 10000) {
            mvh.getTvTie().setText(String.valueOf(mVideos.get(position).getTie()));
        } else if (mVideos.get(position).getTie() >= 10000) {
            mvh.getTvTie().setText(String.valueOf(mVideos.get(position).getTie() / 10000));
        }
        if (mVideos.get(position).getPlayCount() < 10000) {
            mvh.getTvPlayCount().setText(String.valueOf(mVideos.get(position).getPlayCount()));
        } else if (mVideos.get(position).getPlayCount() >= 10000) {
            mvh.getTvPlayCount().setText(String.valueOf(mVideos.get(position).getPlayCount()/10000) + "万");
        }
        mvh.getIvShare().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "分享", Toast.LENGTH_SHORT).show();
            }
        });
        mvh.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    @Override
    public void onClick(View v) {
        if (getItemListener() != null) {
            getItemListener().onItemClick(v,(int)v.getTag());
        } else {
            System.out.println("itemListener is null, you should call setOnItemClickListener");
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private Button btnListPlay, btnClose;
        private CheckBox btnPlayOrPause;
        private TextView tvTitle, tvDesc, tvDuration, tvPlayCount, tvTie;
        private ImageView ivShare;
        private VideoPlayerView vvVideo;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.btnListPlay = (Button) rootView.findViewById(R.id.btnVaRvViewHolderListPlay);
            this.btnPlayOrPause = (CheckBox) rootView.findViewById(R.id.btnVaRvViewHolderPlayOrPause);
            this.btnClose = (Button) rootView.findViewById(R.id.btnVaRvViewHolderClose);
            this.tvTitle = (TextView) rootView.findViewById(R.id.tvVaRvViewHolderTitle);
            this.tvDesc = (TextView) rootView.findViewById(R.id.tvVaRvViewHolderDesc);
            this.tvDuration = (TextView) rootView.findViewById(R.id.tvVaRvViewHolderDuration);
            this.tvPlayCount = (TextView) rootView.findViewById(R.id.tvVaRvViewHolderPlayCount);
            this.tvTie = (TextView) rootView.findViewById(R.id.tvVaRvViewHolderTie);
            this.ivShare = (ImageView) rootView.findViewById(R.id.ivVaRvViewHolderShare);
            this.vvVideo = (VideoPlayerView) rootView.findViewById(R.id.vvVaRvViewHolder);
        }

        public Button getBtnListPlay() {
            return btnListPlay;
        }

        public CheckBox getBtnPlayOrPause() {
            return btnPlayOrPause;
        }

        public Button getBtnClose() {
            return btnClose;
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvDesc() {
            return tvDesc;
        }

        public TextView getTvDuration() {
            return tvDuration;
        }

        public TextView getTvPlayCount() {
            return tvPlayCount;
        }

        public TextView getTvTie() {
            return tvTie;
        }

        public ImageView getIvShare() {
            return ivShare;
        }

        public VideoPlayerView getVvVideo() {
            return vvVideo;
        }

    }

}
