package alan.example.com.persontestdemo;

import android.support.v4.app.Fragment;

/**
 * Created by qk14 on 2017/5/23.
 */

public class FragmentController {

    public static Fragment getFragment(String fragmentName) {

        try {
            return (Fragment) Class.forName(fragmentName).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
