package alan.example.com.persontestdemo.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by qk14 on 2017/5/23.
 */

public abstract class BaseBindingActivity extends BaseActivity {

    protected ViewDataBinding mViewDataBinding;
    protected View mRootView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mRootView = mViewDataBinding.getRoot();
        castBindingView(); // bindging转换
        addLisenter(); // 监听事件
    }

    protected abstract void castBindingView();

}
