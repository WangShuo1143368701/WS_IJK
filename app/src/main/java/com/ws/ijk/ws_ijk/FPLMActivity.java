package com.ws.ijk.ws_ijk;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ws.ijk.wsqj_ijkplayer.widget.LianMaiSplitScreen.AspectTextureView;
import com.ws.ijk.wsqj_ijkplayer.widget.LianMaiSplitScreen.LMVideoView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FPLMActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private static final String TAG = "FPLMActivity";

    @BindView(R.id.txv_preview)
    AspectTextureView mTextureView;
    @BindView(R.id.LM)
    Button LM;
    @BindView(R.id.LMFlowGroupView)
    com.ws.ijk.wsqj_ijkplayer.widget.LianMaiSplitScreen.LMFlowGroupView LMFlowGroupView;
    @BindView(R.id.LMHF)
    Button LMHF;

    private Camera mCamera;

    private int mChildWidth;
    private int mChildHeight;
    private int mChildCount;

    private ArrayList<LMVideoView> LMVideoViewLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fplm);
        ButterKnife.bind(this);

        mTextureView.setSurfaceTextureListener(this);
        LMVideoViewLists = new ArrayList<LMVideoView>();
    }


    @OnClick({R.id.LM, R.id.LMHF})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LM:
                addLMVideoView();
                break;
            case R.id.LMHF:
                recoverLive();
                break;

        }

    }

    private void recoverLive() {
       for(LMVideoView lv :LMVideoViewLists){
           LMFlowGroupView.removeView(lv);
       }
        ViewGroup.LayoutParams params = mTextureView.getLayoutParams();
        params.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mTextureView.setLayoutParams(params);
    }

    private void addLMVideoView() {
        LMVideoView lmVideoView = new LMVideoView(this);
        LMFlowGroupView.addView(lmVideoView);
        LMVideoViewLists.add(lmVideoView);

        lmVideoView.getLMijkVideoView().setVideoPath("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4");
        lmVideoView.getLMijkVideoView().start();
        lmVideoView.getCoverView().setImageResource(R.mipmap.timg);

        updateLMVideoViewLayout(lmVideoView);
        updateAspectTextureLayout(mTextureView);
    }

    private void updateLMVideoViewLayout(LMVideoView view) {
        getLMFlowViewData();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = mChildWidth;
        params.height = mChildHeight;
        view.setLayoutParams(params);
    }


    private void updateAspectTextureLayout(AspectTextureView view) {
        getLMFlowViewData();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = mChildWidth;
        params.height = mChildHeight;
        view.setLayoutParams(params);
    }


    private void getLMFlowViewData() {
        mChildCount = LMFlowGroupView.getChildCount();
        mChildWidth = LMFlowGroupView.getWidth() / mChildCount;
        mChildHeight = (int) (mChildWidth * 1.6);
        Log.e("wangshuo", "mChildCount = " + mChildCount);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open(1);
        if (mCamera == null) {
            // Seeing this on Nexus 7 2012 -- I guess it wants a rear-facing camera, but
            // there isn't one.  TODO: fix
            throw new RuntimeException("Default camera not available");
        }

        try {
            mCamera.setPreviewTexture(surface);
            mCamera.setDisplayOrientation(90);//将相机试图旋转90度，因为默认是横屏显示
            mCamera.startPreview();
        } catch (IOException ioe) {
            // Something bad happened
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        // Ignored, Camera does all the work for us
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        // Invoked every time there's a new Camera preview frame
        //Log.d(TAG, "updated, ts=" + surface.getTimestamp());
    }


}
