package com.ws.ijk.wsqj_ijkplayer.widget.LianMaiSplitScreen;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ws.ijk.wsqj_ijkplayer.R;
import com.ws.ijk.wsqj_ijkplayer.widget.media.LiveIjkVideoView;

/**
 * Created by WangShuo on 2017/7/20.
 */

public class LMVideoView extends FrameLayout {

    private Context mContext;
    private String mPath;

    private View view;
    private LiveIjkVideoView mLiveVideoView;
    private ImageView mCoverView;


    public LMVideoView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LMVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LMVideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LMVideoView(@NonNull Context context ,String path) {
        super(context);
        this.mPath = path;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setBackgroundColor(Color.TRANSPARENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.lm_videoview, null);
        addView(view);

        initView();
    }

    private void initView() {

        if(view != null) {
            mLiveVideoView = (LiveIjkVideoView) view.findViewById(R.id.lm_video_view);
            mCoverView = (ImageView) view.findViewById(R.id.lm_play_coverview);

            mLiveVideoView.setRender(LiveIjkVideoView.RENDER_TEXTURE_VIEW);
            mLiveVideoView.setDisplayAspectRatio(LiveIjkVideoView.s_allAspectRatio[1]);
            mLiveVideoView.setCoverView(mCoverView);
            if(mPath != null){
                mLiveVideoView.setVideoPath(mPath);
            }
        }
    }


    public LiveIjkVideoView getLMijkVideoView(){
        return mLiveVideoView;
    }

    public ImageView getCoverView(){
        return mCoverView;
    }
}
