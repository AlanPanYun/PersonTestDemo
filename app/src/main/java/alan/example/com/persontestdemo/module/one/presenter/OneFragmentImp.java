package alan.example.com.persontestdemo.module.one.presenter;


import java.util.ArrayList;
import java.util.List;

import alan.example.com.persontestdemo.module.one.OneConstract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qk14 on 2017/7/31.
 */

public class OneFragmentImp implements OneConstract.Presenter {


    private OneConstract.View mView;

    public OneFragmentImp(OneConstract.View view) {
        mView = view;
    }

    @Override
    public void loadMore() {

        Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                        Thread.sleep(2000);
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 13; i++) {
                            strings.add(i + " item");
                        }
                        e.onNext(strings);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        mView.onLoadMore((ArrayList<String>) strings);
                    }
                });


    }

    @Override
    public void onRefresh() {
        Observable
                .create(new ObservableOnSubscribe<ArrayList<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<ArrayList<String>> e) throws Exception {
                        Thread.sleep(2000);
                        ArrayList<String> strings = new ArrayList<String>();
                        for (int i = 0; i < 15; i++) {
                            strings.add("ites " + i);
                        }
                        e.onNext(strings);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> list) throws Exception {
                        mView.onRefresh(list);
                    }
                });
    }
}
