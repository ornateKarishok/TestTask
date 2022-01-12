package com.mycompany.testtask.util;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mycompany.testtask.R;

public class FragmentUtil {
    private static void replaceFragment(FragmentManager manager, Fragment fragment, int containerId,
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
        fTrans.replace(containerId, fragment, fragment.getClass().getSimpleName());
        fTrans.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment, int containerId) {
        replaceFragment(manager, fragment, containerId, true, false);
    }

    public static void addFragment(FragmentManager manager, Fragment fragment, int containerId) {
        FragmentTransaction fTrans;
        fTrans = manager.beginTransaction();
        fTrans.add(containerId, fragment);
        fTrans.commitAllowingStateLoss();
    }
}
