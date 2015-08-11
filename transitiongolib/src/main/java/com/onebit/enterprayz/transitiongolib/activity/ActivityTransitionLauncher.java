package com.onebit.enterprayz.transitiongolib.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.onebit.enterprayz.transitiongolib.core.TransitionBundleFactory;

import java.util.Hashtable;

/**
 * Created by takam on 2015/03/26.
 */
public class ActivityTransitionLauncher {
    private static final String TAG = "TransitionLauncher";

    private final Activity activity;
    private static Hashtable<String, View> viewHashtable;


    private ActivityTransitionLauncher(Activity activity) {
        this.activity = activity;
    }

    public static ActivityTransitionLauncher with(Activity activity) {
        viewHashtable = new Hashtable<>();
        return new ActivityTransitionLauncher(activity);
    }

    public ActivityTransitionLauncher transit(String transitionName, View fromView) {
        viewHashtable.put(transitionName, fromView);
        return this;
    }

    public void launch(Intent intent, int requestCode) {
        prepareIntent(intent);
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(0, 0);
    }

    public void launch(Intent intent) {
        prepareIntent(intent);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    private void prepareIntent(Intent intent) {
        Bundle transitionBundle = new Bundle();
        for (String transitName : viewHashtable.keySet()) {
            View fromView = viewHashtable.get(transitName);
            Bitmap bitmap = null;
            if (fromView instanceof ImageView) {
                bitmap = ((BitmapDrawable) ((ImageView) fromView).getDrawable()).getBitmap();
            }
            TransitionBundleFactory.createTransitionBundle(activity, transitionBundle, transitName, fromView, bitmap);
        }
        intent.putExtras(transitionBundle);
    }
}
