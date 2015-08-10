package com.onebit.enterprayz.transitiongolib.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by takam on 2015/05/17.
 */
public class TransitionBundleFactory {
    private static final String TAG = "Transition";

    public static void createTransitionBundle(Context context, Bundle bundle, String transitionName, View fromView, Bitmap bitmap) {
        // Bitmap is Optional
        if (bitmap != null) {
            saveImage(transitionName, bitmap);
        }

        if (fromView instanceof TextView) {
            saveText(transitionName, String.valueOf(((TextView) fromView).getText()));
        }

        int[] screenLocation = new int[2];
        fromView.getLocationOnScreen(screenLocation);
        final TransitionData transitionData = new TransitionData(context, bundle, transitionName, screenLocation[0], screenLocation[1], fromView.getMeasuredWidth(), fromView.getMeasuredHeight());
        transitionData.makeBundle();
    }


    private static void saveText(String transitionName, String text) {
        Cash.add(transitionName, text);
    }

    private static void saveImage(String transitionName, Bitmap bitmap) {
        Cash.add(transitionName, bitmap);
    }

}
