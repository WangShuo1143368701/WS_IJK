package com.ws.ijk.wsqj_ijkplayer.widget.LianMaiSplitScreen;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by WangShuo on 2017/7/21.
 */

public class LMFlowGroupView extends LinearLayout {


    public LMFlowGroupView(Context context) {
        super(context);
        init(context);
    }

    public LMFlowGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LMFlowGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);
    }
}
