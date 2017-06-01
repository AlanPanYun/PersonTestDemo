package alan.example.com.persontestdemo.video;

import android.net.Uri;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by qk14 on 2017/6/1.
 */

public class VideoActivity extends BaseBindingActivity {

    public static final String url = "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640";

    @Override
    protected void castBindingView() {

    }

    @Override
    protected void addLisenter() {

    }

    @Override
    protected void initData() {
        super.initData();
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "hahahah");
        jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(url));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}

