package alan.example.com.persontestdemo.module.one;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by qk14 on 2017/7/25.
 */

public class MainActivityImp implements MainActivityConstract.Presenter{


    @Override
    public void changeToFragment( // fragment切换
                                  @NonNull FragmentManager fragmentManager,
                                  @NonNull Fragment fragment,
                                  @NonNull @IdRes int id,
                                  String tag) {
        if (fragmentManager == null || fragment == null) {
            return;
        }
        FragmentTransaction transaction =
                fragmentManager
                        .beginTransaction()
                        .replace(
                                id,
                                fragment,
                                tag
                        );
        if (transaction == null) {
            return;
        }
        transaction.commit();
    }

}
