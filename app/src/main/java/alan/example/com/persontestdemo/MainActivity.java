package alan.example.com.persontestdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.base.activity.withbar.FragmentTopBarActivity;
import alan.example.com.persontestdemo.base.module.TestFragment;
import alan.example.com.persontestdemo.camera.CameraTwoActivity;
import alan.example.com.persontestdemo.camera.camera1.CustomCamera;
import alan.example.com.persontestdemo.camera.camera1.CustomCamera2;
import alan.example.com.persontestdemo.camera.camera4.Camera4Activity;
import alan.example.com.persontestdemo.databinding.ActivityMainBinding;
import alan.example.com.persontestdemo.recyclerview.RecyclerViewActivity;
import alan.example.com.persontestdemo.statubar.StatuBarMainActivity;
import alan.example.com.persontestdemo.video.VideoActivity;

public class MainActivity extends BaseBindingActivity implements View.OnClickListener {

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    protected void castBindingView() {
        mActivityMainBinding = (ActivityMainBinding) mViewDataBinding;
    }

    @Override
    protected void addLisenter() {
        mActivityMainBinding.button.setOnClickListener(this);
        mActivityMainBinding.buttonStatubar.setOnClickListener(this);
        mActivityMainBinding.buttonRecyclerview.setOnClickListener(this);
        mActivityMainBinding.buttonVideo.setOnClickListener(this);
        mActivityMainBinding.buttonFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        setTitle("adf");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
//                if (Build.VERSION.SDK_INT < 21) {
//                    startActivity(new Intent(this, Camera4Activity.class));
//                }else {
//                    startActivity(new Intent(this, CameraTwoActivity.class));
                    startActivity(new Intent(this, CameraTwoActivity.class));
//                }

//                startActivity(
//                        FragmentTopBarActivity.makeInstance(
//                                this,
//                                TestFragment.class.getName(),
//                                TestFragment.makeArguments(
//                                        "123",
//                                        "alan"
//                                )
//                        )
//                );
                break;

            case R.id.button_statubar:
                startActivity(new Intent(this, StatuBarMainActivity.class));
                break;
            case R.id.button_recyclerview:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.button_FloatingActionButton:
                startActivity(new Intent(this, NavHomeActivity.class));
                break;
            case R.id.button_video:
                startActivity(new Intent(this, VideoActivity.class));
                break;
        }
    }

}
