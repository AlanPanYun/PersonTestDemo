package alan.example.com.persontestdemo.recyclerview.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.recyclerview.viewholder.BaseViewHolder;
import alan.example.com.persontestdemo.recyclerview.viewholder.TestRecyclerViewHolder;

/**
 * Created by qk14 on 2017/5/27.
 */

public class TestRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<String> mItems;
    private Activity mActivity;

    public TestRecyclerAdapter(List<String> items, Activity activity) {
        mItems = items;
        mActivity = activity;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestRecyclerViewHolder(
                LayoutInflater
                        .from(mActivity)
                        .inflate(
                                R.layout.activity_test_recycler_item,
                                parent,
                                false
                        ),
                mActivity
        );
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setModel(mItems.get(position));
        holder.updateView(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

}
