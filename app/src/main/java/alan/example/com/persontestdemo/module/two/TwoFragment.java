package alan.example.com.persontestdemo.module.two;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.fragment.BaseBindingFragment;
import alan.example.com.persontestdemo.module.contract.TwoContract;

/**
 * Created by qk14 on 2017/7/31.
 */

public class TwoFragment extends BaseBindingFragment {


    private TwoContract.Presenter mPresenter;

    @Override
    protected void castBingdingView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    public void initView() {

        mPresenter = new TwoImp();
    }

    @Override
    public void initData() {
//        mPresenter.test1();
//        mPresenter.test2();
//        mPresenter.test3();
//        mPresenter.test4();
        mPresenter.test7();
    }
}
