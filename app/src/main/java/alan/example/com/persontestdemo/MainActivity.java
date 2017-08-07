package alan.example.com.persontestdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.databinding.ActivityMainBinding;
import alan.example.com.persontestdemo.module.five.FiveFragment;
import alan.example.com.persontestdemo.module.four.FourFragment;
import alan.example.com.persontestdemo.module.one.MainActivityConstract;
import alan.example.com.persontestdemo.module.one.MainActivityImp;
import alan.example.com.persontestdemo.module.one.OneHomeFragment;
import alan.example.com.persontestdemo.module.three.ThreeFragment;
import alan.example.com.persontestdemo.module.two.TwoFragment;

public class MainActivity
        extends BaseBindingActivity
        implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private final int FRAGMENT_ID = R.id.fl_detail;
    private ActivityMainBinding mActivityMainBinding;
    private String CURRENT_FRAGMENT = "";
    private FragmentManager mSupportFragmentManager;
    private MainActivityConstract.Presenter mPresenter;
    public static final String TAG_ONE = OneHomeFragment.class.getName();
    public static final String TAG_TWO = TwoFragment.class.getName();
    public static final String TAG_THREE = ThreeFragment.class.getName();
    public static final String TAG_FOUR = FourFragment.class.getName();
    public static final String TAG_FIVE = FiveFragment.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void castBindingView() {
        mActivityMainBinding = (ActivityMainBinding) mViewDataBinding;
    }

    @Override
    protected void addLisenter() {
        mActivityMainBinding.rbOne.setOnCheckedChangeListener(this);
        mActivityMainBinding.rbTwo.setOnCheckedChangeListener(this);
        mActivityMainBinding.rbThree.setOnCheckedChangeListener(this);
        mActivityMainBinding.rbFour.setOnCheckedChangeListener(this);
        mActivityMainBinding.rbFive.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initView() {
        setTitle("adf");

        mActivityMainBinding.rbOne.setChecked(true);
        mSupportFragmentManager = getSupportFragmentManager();
        mPresenter = new MainActivityImp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        changeToFragment(new OneHomeFragment(), null);
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button:
////                if (Build.VERSION.SDK_INT < 21) {
////                    startActivity(new Intent(this, Camera4Activity.class));
////                }else {
////                    startActivity(new Intent(this, CameraTwoActivity.class));
//                    startActivity(new Intent(this, CameraTwoActivity.class));
////                }
//
////                startActivity(
////                        FragmentTopBarActivity.makeInstance(
////                                this,
////                                TestFragment.class.getName(),
////                                TestFragment.makeArguments(
////                                        "123",
////                                        "alan"
////                                )
////                        )
////                );
//                break;
//
//            case R.id.button_statubar:
//                startActivity(new Intent(this, StatuBarMainActivity.class));
//                break;
//            case R.id.button_recyclerview:
//                startActivity(new Intent(this, RecyclerViewActivity.class));
//                break;
//            case R.id.button_FloatingActionButton:
//                startActivity(new Intent(this, NavHomeActivity.class));
//                break;
//            case R.id.button_video:
//                startActivity(new Intent(this, VideoActivity.class));
//                break;
//        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (!b){
            return;
        }
        int id = compoundButton.getId();
        switch (id) {
            case R.id.rb_one:
                changeToFragment(new OneHomeFragment(), TAG_ONE);
                break;
            case R.id.rb_two:
                changeToFragment(new TwoFragment(), TAG_TWO);
                break;
            case R.id.rb_three:

                break;
            case R.id.rb_four:

                break;
            case R.id.rb_five:

                break;
        }
    }

    public void changeToFragment(@NonNull Fragment fragment, String tag) {
        if (!TextUtils.isEmpty(CURRENT_FRAGMENT) && CURRENT_FRAGMENT.equals(tag)) {
            return;
        }
        CURRENT_FRAGMENT = tag;

        mPresenter.changeToFragment(
                mSupportFragmentManager,
                fragment,
                FRAGMENT_ID,
                tag
        );
    }
}
