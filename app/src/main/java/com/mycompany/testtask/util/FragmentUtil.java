package com.mycompany.testtask.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mycompany.testtask.R;

public class FragmentUtil {
    private static void replaceFragment(FragmentManager manager, Fragment fragment,
                                        boolean addToBackStack, boolean useLeftRightAnimations) {
        FragmentTransaction fTrans;
        fTrans = manager.beginTransaction();
        if (!useLeftRightAnimations) {
            fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (addToBackStack) {
            fTrans.addToBackStack(null);
        } else {
            try {
                manager.popBackStackImmediate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fTrans.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        fTrans.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment) {
        replaceFragment(manager, fragment, true, false);
    }
}
