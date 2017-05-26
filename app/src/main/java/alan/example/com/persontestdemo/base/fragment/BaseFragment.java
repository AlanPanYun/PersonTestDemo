package alan.example.com.persontestdemo.base.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import alan.example.com.persontestdemo.base.activity.BaseActivity;
import alan.example.com.persontestdemo.base.activity.withbar.FragmentTopBarActivity;

/**
 * Created by qk14 on 2017/5/23.
 */

public abstract class BaseFragment extends Fragment {


    protected BaseActivity mBaseActivity;
    protected Activity mActivity;
    protected FragmentTopBarActivity mTopBarActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mActivity = getActivity();

        if (mActivity instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) mActivity;
        }
        if (mActivity instanceof FragmentTopBarActivity){
            mTopBarActivity = (FragmentTopBarActivity) getActivity();
        }
    }

    public abstract int getLayoutId();

    public void initView() {


    }

    public void initData() {

    }
    public void addLisenter() {

    }

    protected void setTitle(String title) {
        if (mBaseActivity == null || TextUtils.isEmpty(title)) {
            return;
        }
        mBaseActivity.setTitle(title);
    }

    protected void setTitle(@StringRes @Nullable int title) {
        if (mBaseActivity == null) {
            return;
        }
        mBaseActivity.setTitle(title);
    }
}
