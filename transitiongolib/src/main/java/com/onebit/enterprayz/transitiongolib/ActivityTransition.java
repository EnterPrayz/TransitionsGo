package com.onebit.enterprayz.transitiongolib;

import android.animation.TimeInterpolator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;


import com.onebit.enterprayz.transitiongolib.core.MoveData;
import com.onebit.enterprayz.transitiongolib.core.TransitionActivityAnimation;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by takam on 2015/03/26.
 */
public class ActivityTransition {
    private TimeInterpolator interpolator = new DecelerateInterpolator();
    private Intent fromIntent;
    int duration = 1000;
    private static Hashtable<String, View> viewHashtable;

    private ActivityTransition(Intent intent) {
        this.fromIntent = intent;
    }

    public static ActivityTransition with(Intent intent) {
        viewHashtable = new Hashtable<>();
        return new ActivityTransition(intent);
    }

    public ActivityTransition transit(String transitName, View toView) {
        viewHashtable.put(transitName, toView);
        return this;
    }

    public ActivityTransition duration(int duration) {
        this.duration = duration;
        return this;
    }

    public ActivityTransition interpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }


    public ArrayList<ExitActivityTransition> start(Bundle savedInstanceState) {
        final Bundle bundle = fromIntent.getExtras();
        ArrayList<ExitActivityTransition> res = new ArrayList<>();
        for (String transitName : viewHashtable.keySet()) {
            View toView = viewHashtable.get(transitName);
            final MoveData moveData = new TransitionActivityAnimation().startEnterAnimation(toView.getContext(), transitName, toView, bundle, savedInstanceState, duration, interpolator);
            res.add(new ExitActivityTransition(moveData));
        }
        return res;
    }
}
