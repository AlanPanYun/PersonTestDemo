package alan.example.com.persontestdemo.base.module;

import android.view.View;
import android.widget.Toast;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.fragment.BaseBindingFragment;

/**
 * Created by qk14 on 2017/5/24.
 */

public class TestFragment2 extends BaseBindingFragment {

    @Override
    public void initView() {
        super.initView();
        mBaseActivity.setMenuId(R.menu.main_menu);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test_2;
    }

    @Override
    protected void castBingdingView() {

    }

    @Override
    public void addLisenter() {
        super.addLisenter();
//        mBaseActivity.setTopNavigationListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mActivity,"啊哈哈",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
