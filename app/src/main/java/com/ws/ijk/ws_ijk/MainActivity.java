package com.ws.ijk.ws_ijk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ws.ijk.wsqj_ijkplayer.widget.media.LiveIjkVideoView;

import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity implements IMediaPlayer.OnInfoListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, View.OnClickListener {

    private LiveIjkVideoView liveIjkVideoView ,lmIjkVideoView;
    private ImageView imageView,lmCoverView,lmCloseView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        init();

      /*  DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        String s="屏幕的分辨率为："+dm.widthPixels+"*"+dm.heightPixels;
        Log.e("wangshuo",s);

        Camera.Size size = CameraUtil.getInstance().getBestSize(CameraUtil.getFrontCameraPreviewSize(),640);

        if(size==null){
            Log.e("wangshuo", "size==null");
        }else{
            Log.e("wangshuo","size : "+size.width+"*"+size.height);
        }

        Camera.Size size2 = CameraUtil.getInstance().getBestSize(CameraUtil.getFrontCameraVideoSize(),640);
        if(size2==null){
            Log.e("wangshuo", "size2==null");
        }else{
            Log.e("wangshuo","size : "+size2.width+"*"+size2.height);
        }


        boolean b = CameraUtil.isTablet(this);
        Log.e("wangshuo","b : "+b);*/
    }



    private void initView() {
        liveIjkVideoView= (LiveIjkVideoView) findViewById(R.id.ijkvideo_view);
        imageView = (ImageView) findViewById(R.id.live_play_coverview);
        liveIjkVideoView.setOnInfoListener(this);
        liveIjkVideoView.setOnPreparedListener(this);
        liveIjkVideoView.setOnCompletionListener(this);
        liveIjkVideoView.setOnErrorListener(this);

       //added LM start
        lmIjkVideoView = (LiveIjkVideoView) findViewById(R.id.lm_video_view);
        lmCoverView = (ImageView) findViewById(R.id.lm_play_coverview);
        lmCloseView = (ImageView) findViewById(R.id.lm_play_closeview);
        lmCloseView.setOnClickListener(this);
        //added LM end
    }
    private void init() {
        liveIjkVideoView.setVideoPath("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4");
        liveIjkVideoView.start();


        imageView.setImageResource(R.mipmap.timg);
        liveIjkVideoView.setCoverView(imageView);

        liveIjkVideoView.setRender(LiveIjkVideoView.RENDER_TEXTURE_VIEW);//这里不生效，是info监听自动改了
        liveIjkVideoView.setPlayerRotation(120);

        liveIjkVideoView.setDisplayAspectRatio(LiveIjkVideoView.s_allAspectRatio[1]);


        //added LM start
        lmIjkVideoView.setVideoPath("https://raw.githubusercontent.com/danikula/AndroidVideoCache/master/files/orange1.mp4");
        lmIjkVideoView.start();

        lmCoverView.setImageResource(R.mipmap.timg);
        lmIjkVideoView.setCoverView(lmCoverView);
        lmIjkVideoView.setDisplayAspectRatio(LiveIjkVideoView.s_allAspectRatio[1]);
        //added LM end
    }

    @Override
    protected void onStop() {
        super.onStop();
        liveIjkVideoView.stopPlayback();
        lmIjkVideoView.stopPlayback(); //added LM
    }

    @Override
    protected void onPause() {
        super.onPause();
        liveIjkVideoView.pause();
        lmIjkVideoView.pause(); //added LM
    }

    @Override
    protected void onResume() {
        super.onResume();
        liveIjkVideoView.start();
        lmIjkVideoView.start(); //added LM


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.e("wangshuo","onInfo=  "+i +" onInfo=  "+i1);
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.e("wangshuo","onError=  "+i +" onError=  "+i1);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lm_play_closeview:
                Toast.makeText(MainActivity.this,"close",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
