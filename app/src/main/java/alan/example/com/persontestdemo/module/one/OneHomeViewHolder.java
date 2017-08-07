package alan.example.com.persontestdemo.module.one;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import alan.example.com.persontestdemo.R;
import alan.example.com.persontestdemo.recyclerview.viewholder.BaseViewHolder;
import alan.example.com.persontestdemo.x.XMainActivity;

/**
 * Created by qk14 on 2017/7/25.
 */

public class OneHomeViewHolder
        extends BaseViewHolder
        implements View.OnClickListener {

    private TextView tvMessage;

    public OneHomeViewHolder(View itemView, Activity activity) {
        super(itemView, activity);

    }

    @Override
    protected void findView(View itemView) {
        super.findView(itemView);
        tvMessage = (TextView) itemView.findViewById(R.id.tv_message);

    }

    @Override
    public void updateView(int position) {
        super.updateView(position);
        String s = (String) getModel();
        tvMessage.setText(s);
    }

    @Override
    protected void addLisenter() {
        mRootView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (mPosition == 0){
            mActivity.startActivity(new Intent(mActivity, XMainActivity.class));
        }
        Toast.makeText(mActivity,"hah",Toast.LENGTH_SHORT).show();
    }
}
