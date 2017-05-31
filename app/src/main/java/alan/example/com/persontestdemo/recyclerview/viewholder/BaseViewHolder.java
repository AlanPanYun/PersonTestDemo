package alan.example.com.persontestdemo.recyclerview.viewholder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qk14 on 2017/5/27.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected Activity mActivity;
    protected View mRootView;
    public Object mObject;
    protected int mPosition;

    public BaseViewHolder(View itemView, Activity activity) {
        super(itemView);
        mActivity = activity;
        findView(itemView);
    }

    protected void findView(View itemView) {
        mRootView = itemView;
    }

    public void setModel(Object object) {
        mObject = object;
    }

    public void updateView(int position) {
        mPosition = position;
    }

    public Object getModel() {
        return mObject;
    }

}
