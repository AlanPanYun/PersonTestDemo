package alan.example.com.persontestdemo.base.module;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.fragment.BaseBindingFragment;
import alan.example.com.persontestdemo.customview.CameraActivity;
import alan.example.com.persontestdemo.customview.TestCameraActivity;
import alan.example.com.persontestdemo.databinding.FragmentTestBinding;

/**
 * Created by qk14 on 2017/5/23.
 */

public class TestFragment
        extends BaseBindingFragment
        implements View.OnClickListener {

    private FragmentTestBinding mTestBinding;
    public static final String ID = "id";
    public static final String NAME = "name";


    @Override
    public void initView() {
        setTitle("青稞");
//        mBaseActivity.setMenuId(R.menu.main_menu);
        mTestBinding.cb.setText("12000000.00");
        mTestBinding.cb.setTextColor(Color.RED);
        mTestBinding.cb.setSweepAngle(240);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        String string = arguments.getString(NAME);
        setTitle(string);
    }

    public static Bundle makeArguments(String id, String name) {
        Bundle bundle = new Bundle();
        bundle.putString(ID, id);
        bundle.putString(NAME, name);
        return bundle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void addLisenter() {
        mTestBinding.tvTest.setOnClickListener(this);
        mBaseActivity.setmMenuItemClick(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Toast.makeText(mBaseActivity, "hahah", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

//        mBaseActivity.setTopNavigationListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mTopBarActivity.onBackPressed();
//            }
//        });
//        mTopBarActivity.setOnBackPressdAction(new OnBackPressdActionInterface() {
//            @Override
//            public void onAction() {
//                Toast.makeText(mActivity,"hahaa",Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    protected void castBingdingView() {
        mTestBinding = (FragmentTestBinding) mViewDataBinding;
    }


    @Override
    public void onClick(View view) {
//        startActivity(FragmentTopBarActivity.makeInstance(
//                mActivity,
//                TestFragment2.class.getName(),
//                null
//        ));
//        mTopBarActivity.navigateToMemoryFragment(
//                new TestFragment2(),
//                TestFragment2.class.getSimpleName()
//        );
        startActivity(new Intent(mActivity, TestCameraActivity.class));
    }
}
