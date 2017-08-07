package alan.example.com.persontestdemo.module.one;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.base.fragment.BaseBindingFragment;
import alan.example.com.persontestdemo.databinding.FragmentOneHomeBinding;
import alan.example.com.persontestdemo.module.one.presenter.OneFragmentImp;

/**
 * Created by qk14 on 2017/7/25.
 */

public class OneHomeFragment extends BaseBindingFragment implements OneConstract.View {

    private FragmentOneHomeBinding mOneHomeBinding;
    private ArrayList<String> mItems = new ArrayList<>();
    private OneHomeAdapter oneHomeAdapter;
    private OneFragmentImp oneFragmentImp;

    @Override
    protected void castBingdingView() {
        mOneHomeBinding = (FragmentOneHomeBinding) mViewDataBinding;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one_home;
    }

    @Override
    public void initView() {
        oneHomeAdapter = new OneHomeAdapter(mActivity, mItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mOneHomeBinding.XRecyclerView.setLayoutManager(layoutManager);
        mOneHomeBinding.XRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mOneHomeBinding.XRecyclerView.setAdapter(oneHomeAdapter);
        mOneHomeBinding.XRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallTrianglePath);
        mOneHomeBinding.XRecyclerView.setFootView(LayoutInflater.from(mContext).inflate(R.layout.view_foot, null));
    }

    @Override
    public void initData() {

        for (int i = 0; i < 20; i++) {
            mItems.add("items " + i);
        }
        oneHomeAdapter.notifyDataSetChanged();

        oneFragmentImp = new OneFragmentImp(this);
    }

    @Override
    public void addLisenter() {
        mOneHomeBinding.XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                oneFragmentImp.onRefresh();
            }

            @Override
            public void onLoadMore() {
                // load more data here
                oneFragmentImp.loadMore();
            }
        });
    }

    @Override
    public void onLoadMore(ArrayList<String> list) {
        Log.i("onLoadMore", list.toString());
        mItems.addAll(list);
        oneHomeAdapter.notifyDataSetChanged();
        mOneHomeBinding.XRecyclerView.refreshComplete();
    }

    @Override
    public void onRefresh(ArrayList<String> list) {
        Log.i("onRefresh", list.toString());
        mItems.clear();
        mItems.addAll(list);
        oneHomeAdapter.notifyDataSetChanged();
        mOneHomeBinding.XRecyclerView.refreshComplete();
    }
}
