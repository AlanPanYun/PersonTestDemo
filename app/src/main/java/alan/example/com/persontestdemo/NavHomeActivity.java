package alan.example.com.persontestdemo;

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

    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setTranslucentForImageView(this, 0, mNavHomeBinding.navHomeToolbar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nav_home;
    }
}
