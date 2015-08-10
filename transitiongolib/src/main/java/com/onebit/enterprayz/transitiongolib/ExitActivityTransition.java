package com.onebit.enterprayz.transitiongolib;

import android.app.Activity;

import com.onebit.enterprayz.transitiongolib.core.MoveData;
import com.onebit.enterprayz.transitiongolib.core.TransitionActivityAnimation;

/**
 * Created by takam on 2015/03/30.
 */
public class ExitActivityTransition {
    private final MoveData moveData;


    public ExitActivityTransition(MoveData moveData) {
        this.moveData = moveData;
    }

    public void exit(final Activity activity) {
        new TransitionActivityAnimation().startExitAnimation(moveData, new Runnable() {
            @Override
            public void run() {
                activity.finish();
                activity.overridePendingTransition(0, 0);
            }
        });
    }
}
