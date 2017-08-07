package alan.example.com.persontestdemo.module.one;

import java.util.ArrayList;

import alan.example.com.persontestdemo.module.contract.BaseContract;

/**
 * Created by qk14 on 2017/8/7.
 */

public interface OneConstract {

    interface View extends BaseContract.View {
        void onLoadMore(ArrayList<String> list);

        void onRefresh(ArrayList<String> list);
    }

    interface Presenter extends BaseContract.Presenter {

        void loadMore();

        void onRefresh();
    }
}
