package alan.example.com.persontestdemo.module.one;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by qk14 on 2017/7/25.
 */

public interface MainActivityConstract {

    interface View{

    }

    interface Presenter{
       void  changeToFragment( // fragment切换
                          @NonNull FragmentManager fragmentManager,
                          @NonNull Fragment fragment,
                          @NonNull @IdRes int id,
                          String tag);
    }

}
