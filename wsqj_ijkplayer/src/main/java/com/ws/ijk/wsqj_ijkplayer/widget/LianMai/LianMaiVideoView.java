package com.ws.ijk.wsqj_ijkplayer.widget.LianMai;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.ws.ijk.wsqj_ijkplayer.widget.media.LiveIjkVideoView;

/**
 * Created by WangShuo on 2017/6/29.
 */

public class LianMaiVideoView extends FrameLayout {

    private View mLiveVideoView;

    public LianMaiVideoView(@NonNull Context context) {
        super(context);
        init();
    }

    public LianMaiVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LianMaiVideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mLiveVideoView = getChildAt(0);
        if(mLiveVideoView instanceof LiveIjkVideoView) {
            ((LiveIjkVideoView) mLiveVideoView).setRender(LiveIjkVideoView.RENDER_TEXTURE_VIEW);
        }
    }
}
