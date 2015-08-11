package com.onebit.enterprayz.transitiongolib.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.onebit.enterprayz.transitiongolib.activity.ExitActivityTransition;
import com.onebit.enterprayz.transitiongolib.activity.TransitionActivityAnimation;
import com.onebit.enterprayz.transitiongolib.core.MoveData;
import com.onebit.enterprayz.transitiongolib.core.TransitionBundleFactory;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by urec on 10.08.15.
 */
public class ViewTransitionLauncher {

    private static Hashtable<String, ViewTransition> viewHashtable;
    private static Runnable endOfAction;

    private int maxDuration = 0;

    public static ViewTransitionLauncher make() {
        viewHashtable = new Hashtable<>();
        return new ViewTransitionLauncher();
    }

    public ViewTransitionLauncher transit(String transitionName, ViewTransition transitionObj) {
        viewHashtable.put(transitionName, transitionObj);
        return this;
    }

    public ViewTransitionLauncher addEndListener(Runnable endOfAction) {
        ViewTransitionLauncher.endOfAction = endOfAction;
        return this;
    }

    public ArrayList<ExitViewTransitAnimation> launch(Context context) {
        Bundle transitionBundle = new Bundle();
        for (String transitName : viewHashtable.keySet()) {
            ViewTransition transition = viewHashtable.get(transitName);
            Bitmap bitmap = null;
            if (transition.from instanceof ImageView) {
                bitmap = ((BitmapDrawable) ((ImageView) transition.from).getDrawable()).getBitmap();
            }
            TransitionBundleFactory.createTransitionBundle(context, transitionBundle, String.valueOf(transition.from.getId()), transition.from, bitmap);
        }
        ArrayList<ExitViewTransitAnimation> res = new ArrayList<>();
        for (String transitName : viewHashtable.keySet()) {
            ViewTransition transition = viewHashtable.get(transitName);
            maxDuration = Math.max(maxDuration, transition.getDuration());
            final MoveData moveData = new TransitionViewAnimation().startEnterAnimation(context, String.valueOf(transition.from.getId()), transition.to, transition.from, transitionBundle, transition.getDuration(), transition.getInterpolator());
            res.add(new ExitViewTransitAnimation(moveData));
        }
        if (endOfAction != null) {
            viewHashtable.elements().nextElement().from.postDelayed(endOfAction, maxDuration);
        }
        return res;
    }
}
