package alan.example.com.persontestdemo;

import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.databinding.ActivityCollisationBinding;

/**
 * Created by qk14 on 2017/5/27.
 */

public class NavHomeActivity extends BaseBindingActivity {

    private ActivityCollisationBinding mCollisationBinding;

    @Override
    protected void castBindingView() {
        mCollisationBinding = (ActivityCollisationBinding) mViewDataBinding;
    }

    @Override
    protected void addLisenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collisation;
    }
}
