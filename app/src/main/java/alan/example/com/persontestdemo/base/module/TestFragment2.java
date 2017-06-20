package alan.example.com.persontestdemo.base.module;

import android.util.Log;

import java.util.Arrays;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.fragment.BaseBindingFragment;
import alan.example.com.persontestdemo.customview.WheelView;
import alan.example.com.persontestdemo.databinding.FragmentTest2Binding;

/**
 * Created by qk14 on 2017/5/24.
 */

public class TestFragment2 extends BaseBindingFragment {

    private FragmentTest2Binding test2Binding;
    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};


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
        test2Binding = (FragmentTest2Binding) mViewDataBinding;
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

//        test2Binding.wheelview.setOffset(1);
        test2Binding.wheelview.setItems(Arrays.asList(PLANETS));
        test2Binding.wheelview.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d("alan", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }
}
