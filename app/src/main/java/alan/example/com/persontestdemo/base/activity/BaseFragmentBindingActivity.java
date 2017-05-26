package alan.example.com.persontestdemo.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.View;

import java.util.List;

import alan.example.com.persontestdemo.FragmentController;
import alan.example.com.persontestdemo.base.activity.withbar.FragmentTopBarActivity;

/**
 * Created by qk14 on 2017/5/22.
 */

public abstract class BaseFragmentBindingActivity extends BaseBindingActivity {

    public static final String FRAGMENT_CLASS_NAME = "Fragment_ClassName";
    public static final String FRAGMENT_ARGUMENTS = "Fragment_Arguments";
    private FragmentManager mFragmentManager;
    private BaseFragment mCurrentFragment;
    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        Bundle budle = intent.getExtras();
        if (budle != null) {
            String mClassName = budle.getString(FRAGMENT_CLASS_NAME);
            Bundle mFragmentArguments = budle.getParcelable(FRAGMENT_ARGUMENTS);

            fragment = FragmentController.getFragment(mClassName);
            if (fragment != null && mFragmentArguments != null) {
                fragment.setArguments(mFragmentArguments);
            }
        }
        initArguments();
    }

    public static Intent makeInstance(
            Context context,
            String className,
            Bundle bundle) {
        Intent intent = new Intent(context, FragmentTopBarActivity.class);
        intent.putExtra(FRAGMENT_CLASS_NAME, className);
        intent.putExtra(FRAGMENT_ARGUMENTS, bundle);
        return intent;
    }

    public static void makeInstanceInOneActivity(
            Fragment fragment,
            Bundle bundle) {
        if (fragment != null) {
            fragment.setArguments(bundle);
        }
    }

    private void initArguments() {

        if (fragment != null) {
            navigateToNoMemoryFragment(fragment, fragment.getClass().getSimpleName());
        }
    }

    public void navigateToNoMemoryFragment(
            Fragment fragment,
            String tag
    ) {
        navigateToFragment(fragment, null, false, tag);
    }

    public void navigateToMemoryFragment(
            Fragment fragment,
            String tag
    ) {
        navigateToFragment(fragment, null, true, tag);
    }


    public void navigateToMemoryFragment(
            Fragment fragment,
            List<Pair<View, String>> transitions,
            String tag
    ) {
        navigateToFragment(fragment, transitions, true, tag);
    }

    public void navigateToNoMemoryFragment(
            Fragment fragment,
            List<Pair<View, String>> transitions,
            String tag
    ) {
        navigateToFragment(fragment, transitions, false, tag);
    }

    private void navigateToFragment(
            Fragment fragment,
            List<Pair<View, String>> trans,
            boolean memory,
            String tag) {

        if (fragment == null || mFragmentManager == null) {
            return;
        }
        if (fragment instanceof BaseFragment) {
            mCurrentFragment = (BaseFragment) fragment;
        }

        FragmentTransaction transaction =
                mFragmentManager
                        .beginTransaction()
                        .replace(
                                getFragmentLayoutId(),
                                fragment,
                                tag
                        );
        if (memory) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        if (trans == null) {
            transaction.commit();
            return;
        }

        int size = trans.size();
        for (int i = 0; i < size; i++) {
            Pair<View, String> pair = trans.get(i);
            if (pair == null) {
                continue;
            }
            transaction.addSharedElement(pair.first, pair.second);
        }
        transaction.commit();
    }

    public abstract int getFragmentLayoutId();
}
