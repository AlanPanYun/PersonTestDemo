package alan.example.com.persontestdemo.base.activity;

import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Created by qk14 on 2017/5/23.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected int mMenuId = 0;
    protected Toolbar.OnMenuItemClickListener mMenuItemClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    public void setTitle(String title) {

    }

    public void setTitle(@StringRes @Nullable int title) {

    }


    protected abstract void addLisenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    public void setMenuId(@MenuRes int menuId) {
        mMenuId = menuId;
    }

    // 有topbar
    public void setmMenuItemClick(Toolbar.OnMenuItemClickListener menuItemClick){

    };

    public void setTopNavigationListener(View.OnClickListener onClickListener){

    }

}
