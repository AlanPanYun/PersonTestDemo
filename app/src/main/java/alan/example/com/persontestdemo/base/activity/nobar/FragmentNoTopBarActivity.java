package alan.example.com.persontestdemo.base.activity.nobar;

import android.support.v7.widget.Toolbar;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.activity.BaseFragmentBindingActivity;
import alan.example.com.persontestdemo.databinding.ActivityDetialNoTopBarBinding;

/**
 * Created by qk14 on 2017/5/23.
 */

public class FragmentNoTopBarActivity extends BaseFragmentBindingActivity {


    private ActivityDetialNoTopBarBinding mNoTopBarBinding;

    @Override
    protected void castBindingView() {
        mNoTopBarBinding = (ActivityDetialNoTopBarBinding) mViewDataBinding;
    }

    @Override
    protected void addLisenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detial_no_top_bar;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.id.fl_detail;
    }
}
