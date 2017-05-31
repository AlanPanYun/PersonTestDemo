package alan.example.com.persontestdemo.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by qk14 on 2017/5/23.
 */

public abstract class BaseBindingActivity extends BaseActivity {

    protected ViewDataBinding mViewDataBinding;
    protected View mRootView;

    @Override
    protected void initBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        castBindingView(); // bindging转换
        mRootView = mViewDataBinding.getRoot();
    }

    protected abstract void castBindingView();

}
