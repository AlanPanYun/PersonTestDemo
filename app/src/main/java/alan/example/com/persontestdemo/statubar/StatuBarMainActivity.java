package alan.example.com.persontestdemo.statubar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.databinding.ActivityMainStatuBarBinding;

public class StatuBarMainActivity extends BaseBindingActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainStatuBarBinding mStatuBarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //StatusBarCompat.compat(this, 0xFFFF0000);
        StatusBarCompat.compat(this);

    }

    @Override
    protected void addLisenter() {
        mStatuBarBinding.nvNaviagtion.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_statu_bar;
    }

    @Override
    protected void castBindingView() {
        mStatuBarBinding = (ActivityMainStatuBarBinding) mViewDataBinding;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this,"小时",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_discussion:
                Toast.makeText(this,"小时",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
