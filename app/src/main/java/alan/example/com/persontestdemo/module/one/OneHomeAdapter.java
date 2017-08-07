package alan.example.com.persontestdemo.module.one;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.utils.CollectionUtil;

/**
 * Created by qk14 on 2017/7/25.
 */

public class OneHomeAdapter extends RecyclerView.Adapter<OneHomeViewHolder> {

    private Activity mContext;
    private List<String> mItems;

    public OneHomeAdapter(Activity context,List<String> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public OneHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneHomeViewHolder(
                LayoutInflater
                        .from(mContext)
                        .inflate(
                                R.layout.adapter_one_home,parent,false),
                mContext
        );
    }

    @Override
    public void onBindViewHolder(OneHomeViewHolder holder, int position) {
        holder.setModel(mItems.get(position));
        holder.updateView(position);
    }

    @Override
    public int getItemCount() {
        return CollectionUtil.size(mItems);
    }
}
