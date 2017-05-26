package alan.example.com.persontestdemo.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qk14 on 2017/5/23.
 */

public abstract class BaseBindingFragment extends BaseFragment {

    protected ViewDataBinding mViewDataBinding;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mViewDataBinding = DataBindingUtil.inflate(
                inflater,
                getLayoutId(),
                container,
                false
        );
        castBingdingView();
        initView();
        addLisenter();
        initData();
        return mViewDataBinding.getRoot();
    }

    protected abstract void castBingdingView();

}
