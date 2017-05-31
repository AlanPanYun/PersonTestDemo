package alan.example.com.persontestdemo.recyclerview;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.activity.BaseBindingActivity;
import alan.example.com.persontestdemo.databinding.ActivityRecyclerViewBinding;
import alan.example.com.persontestdemo.recyclerview.adapter.TestRecyclerAdapter;

/**
 * Created by qk14 on 2017/5/27.
 */

public class RecyclerViewActivity extends BaseBindingActivity {


    private ActivityRecyclerViewBinding mRecyclerViewBinding;

    @Override
    protected void castBindingView() {
        mRecyclerViewBinding = (ActivityRecyclerViewBinding) mViewDataBinding;
    }

    @Override
    protected void addLisenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void initView() {
        super.initView();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            list.add("test " + i);
        }
        TestRecyclerAdapter recyclerAdapter = new TestRecyclerAdapter(list, this);
        mRecyclerViewBinding.rvItems.setAdapter(recyclerAdapter);
        mRecyclerViewBinding.rvItems.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewBinding.rvItems.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerViewBinding.rvItems.addItemDecoration(new FlagItemDecoration(this));
        mRecyclerViewBinding.rvItems.addItemDecoration(new ColorDividerItemDecoration());
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
