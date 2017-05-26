package alan.example.com.persontestdemo.base.activity.withbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.activity.BaseFragmentBindingActivity;
import alan.example.com.persontestdemo.base.inter.OnBackPressdActionInterface;
import alan.example.com.persontestdemo.databinding.ActivityDetailTopBarBinding;

/**
 * Created by qk14 on 2017/5/23.
 */

public class FragmentTopBarActivity extends BaseFragmentBindingActivity {

    protected Toolbar mToolbar;
    protected ActionBar mActionBar;
    private ActivityDetailTopBarBinding mDetailTopBarBinding;
    protected OnBackPressdActionInterface mBackPressdActionInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopBar(true); // topbar设置
        Log.i("FragmentTopBarActivity", "FragmentTopBarActivity   create");
    }


    @Override
    protected void addLisenter() {

    }

    @Override
    protected void initView() {

    }

    protected void initTopBar(boolean b) {
        mToolbar = getTopBar();
        if (mToolbar != null) {
            mToolbar.setTitle("青客");
            setSupportActionBar(mToolbar);
        }
        mActionBar = getSupportActionBar();
        setActionBarBackAction(b);
        setTopNavigationListener(null);
    }

    private void setActionBarBackAction(boolean b) {
        if (mActionBar == null) {
            return;
        }
        Log.i("action.", "aciont ys");
        mActionBar.setDisplayHomeAsUpEnabled(b);
        mActionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.id.fl_detail;
    }

    @Override
    public void setmMenuItemClick(Toolbar.OnMenuItemClickListener menuItemClick) {
        mMenuItemClick = menuItemClick;
        if (mMenuItemClick != null && mToolbar != null) {
            mToolbar.setOnMenuItemClickListener(mMenuItemClick);
        }
    }


    @Override
    public void setTopNavigationListener(View.OnClickListener onClickListener) {
        if (mToolbar == null) {
            return;
        }
        if (onClickListener == null) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } else {
            mToolbar.setNavigationOnClickListener(onClickListener);
        }
    }

    protected Toolbar getTopBar() {
        return mDetailTopBarBinding.tbTop;
    }


    @Override
    public void setTitle(String title) {
        mDetailTopBarBinding.tvTitle.setText(title);
    }

    @Override
    public void setTitle(@StringRes @Nullable int title) {
        mDetailTopBarBinding.tvTitle.setText(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_top_bar;
    }

    @Override
    protected void castBindingView() {
        mDetailTopBarBinding = (ActivityDetailTopBarBinding) mViewDataBinding;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mMenuId > 0) {
            getMenuInflater().inflate(mMenuId, menu);
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mBackPressdActionInterface == null) {
            super.onBackPressed();
        } else {
            mBackPressdActionInterface.onAction();
        }
    }

    public void setOnBackPressdAction(OnBackPressdActionInterface onBackPressdActionInterface) {
        mBackPressdActionInterface = onBackPressdActionInterface;
    }

}
