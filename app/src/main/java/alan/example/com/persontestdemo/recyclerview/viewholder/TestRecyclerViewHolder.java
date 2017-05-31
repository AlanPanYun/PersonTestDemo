package alan.example.com.persontestdemo.recyclerview.viewholder;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.View;

import alan.example.com.persontestdemo.databinding.ActivityTestRecyclerItemBinding;

/**
 * Created by qk14 on 2017/5/27.
 */

public class TestRecyclerViewHolder extends BaseViewHolder {

    private ActivityTestRecyclerItemBinding mBinding;

    public TestRecyclerViewHolder(View itemView, Activity activity) {
        super(itemView, activity);
    }

    @Override
    protected void findView(View itemView) {
        super.findView(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void updateView(int position) {
        super.updateView(position);
        String s = (String) getModel();
        mBinding.tvTestItem.setText(s);
    }
}
