package alan.example.com.persontestdemo;

import android.view.View;

import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.databinding.ActivityNavHomeBinding;
import alan.example.com.persontestdemo.utils.StatusBarUtil;

/**
 * Created by qk14 on 2017/5/27.
 */

public class NavHomeActivity extends BaseBindingActivity {

    private ActivityNavHomeBinding mNavHomeBinding;

    @Override
    protected void castBindingView() {
        mNavHomeBinding = (ActivityNavHomeBinding) mViewDataBinding;
    }

    @Override
    protected void addLisenter() {
        mNavHomeBinding.navHomeToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
//        mNavHomeBinding.navHomeToolbar.setNavigationContentDescription();
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setTranslucentForImageView(
                this,
                0,
                mNavHomeBinding.navHomeToolbar
        );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nav_home;
    }
}
