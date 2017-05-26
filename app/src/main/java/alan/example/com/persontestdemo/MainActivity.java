package alan.example.com.persontestdemo;

import android.os.Bundle;
import android.view.View;

import alan.example.com.persontestdemo.base.activity.BaseActivity;
import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.base.activity.withbar.FragmentTopBarActivity;
import alan.example.com.persontestdemo.base.module.TestFragment;
import alan.example.com.persontestdemo.databinding.ActivityMainBinding;

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

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        startActivity(
                FragmentTopBarActivity.makeInstance(
                        this,
                        TestFragment.class.getName(),
                        TestFragment.makeArguments(
                                "123",
                                "alan"
                        )
                )
        );
    }

}
