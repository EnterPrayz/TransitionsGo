package com.onebit.enterprayz.transitiongolib.view;

import com.onebit.enterprayz.transitiongolib.core.MoveData;

/**
 * Created by urec on 02.08.15.
 */
public class ExitViewTransitAnimation {
    private MoveData moveData;

    public ExitViewTransitAnimation(MoveData moveData) {
        this.moveData = moveData;
    }

    public void exit() {
        new TransitionViewAnimation().startExitAnimation(moveData, null);
    }
}
