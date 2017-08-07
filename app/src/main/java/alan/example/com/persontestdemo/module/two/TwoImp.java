package alan.example.com.persontestdemo.module.two;

import android.support.annotation.NonNull;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import alan.example.com.persontestdemo.module.contract.TwoContract;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qk14 on 2017/8/2.
 */

public class TwoImp implements TwoContract.Presenter {

    public static final String TAG = "TAG_RX";

    private static CompositeDisposable mCompositeDisposable;

    static {
        RxJavaPlugins.setErrorHandler(new Consumer() {

            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof InterruptedIOException) {
                    Log.d(TAG, "Io interrupted");
                }
            }

        });


    }

    public TwoImp() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void test1() {
        // 创建 一个上游 observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "onNext = " + 1);
                e.onNext(1);
                Log.d(TAG, "onNext = " + 2);
                e.onNext(2);
                Log.d(TAG, "onNext = " + 3);
                e.onNext(3);
                e.onComplete();
                Log.d(TAG, "onNext = " + 4);
                e.onNext(4);

            }
        });

        // 创建下游 observer
        Observer<Integer> observer = new Observer<Integer>() {
            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
                mDisposable = d;
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "value = " + value);
                if (value == 2) {
                    mDisposable.dispose();
                    Log.d(TAG, "mDisposable");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "e = " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "consumer = " + integer);
            }
        };

        // 创建连接
        observable.subscribe(consumer);
    }

    @Override
    public void test2() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "exchange +" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "value = " + s);
            }
        });

    }

    @Override
    public void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }

                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

                Log.d(TAG, "VALUE = " + o.toString());
            }
        });

    }

    @Override
    public void test4() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Thread.sleep(3000);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit complete1");
                Log.d(TAG, "Observer 1 :" + Thread.currentThread().getName());

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Log.d(TAG, "emit C");
                emitter.onNext("C");
//                emitter.onNext("D");
                Log.d(TAG, "Observer 2 :" + Thread.currentThread().getName());

                Log.d(TAG, "emit complete2");
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " Disposable");
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, " onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " Throwable = " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete = ");
            }
        });
    }

    @Override
    public void test5() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        for (int i = 0; ; i++) {
                            e.onNext(i);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {

                        return integer % 10 == 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "test5 " + integer);
                    }
                });
    }

    @Override
    public void test6() {
        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                        Log.i(TAG, "current requested: " + e.requested());
                    }
                }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(100);
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void test7() {
        Flowable
                .fromArray(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                        Log.e(TAG, "accept: take : " + integer + "\n");
                    }
                });

        Observable
                .just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                        Log.e(TAG, "skip : " + integer + "\n");
                    }
                });


        Observable
                .just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {

                        Log.e(TAG, "buffer size : " + integers.size() + "\n");

                        Log.e(TAG, "buffer value : ");
                        for (Integer i : integers) {

                            Log.e(TAG, i + "");
                        }

                        Log.e(TAG, "\n");
                    }
                });

        Observable.just(1, 1, 1, 2, 2, 3, 4, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                        Log.e(TAG, "distinct : " + integer + "\n");
                    }
                });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception { // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(600);
                // emitter.onNext(2); // deliver
                Thread.sleep(200);
                // emitter.onNext(3); // skip
                Thread.sleep(100);
                // emitter.onNext(4); // deliver
                Thread.sleep(605);
                // emitter.onNext(5); // deliver
                Thread.sleep(510);
                // emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                        Log.e(TAG, "debounce :" + integer + "\n");
                    }
                });


    }
}
