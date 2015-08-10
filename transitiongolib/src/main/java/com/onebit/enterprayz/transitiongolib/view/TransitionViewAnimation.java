package com.onebit.enterprayz.transitiongolib.view;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.onebit.enterprayz.transitiongolib.abstractions.TransitionAnimation;
import com.onebit.enterprayz.transitiongolib.core.MoveData;
import com.onebit.enterprayz.transitiongolib.core.TransitionData;

/**
 * Created by urec on 10.08.15.
 */
public class TransitionViewAnimation extends TransitionAnimation {

    private View fromView;

    public MoveData startEnterAnimation(Context context, String transitionName, final View toView, final View fromView, Bundle transitionBundle, int duration, final TimeInterpolator interpolator) {
        this.fromView = fromView;
        this.fromView.setVisibility(View.INVISIBLE);
        final TransitionData transitionData = new TransitionData(context, transitionBundle, transitionName);
        trySetImageToView(toView, transitionName);
        trySetTextToView(toView, transitionName);
        final MoveData moveData = new MoveData();
        moveData.toView = toView;
        moveData.duration = duration;

        int[] screenLocation = new int[2];
        toView.getLocationOnScreen(screenLocation);
        moveData.leftDelta = transitionData.thumbnailLeft - screenLocation[0];
        moveData.topDelta = transitionData.thumbnailTop - screenLocation[1];

        moveData.widthScale = (float) transitionData.thumbnailWidth / toView.getWidth();
        moveData.heightScale = (float) transitionData.thumbnailHeight / toView.getHeight();

        runActivityEnterAnimation(moveData, interpolator);

        return moveData;
    }

    private static void runActivityEnterAnimation(MoveData moveData, TimeInterpolator interpolator) {
        final View toView = moveData.toView;
        toView.setPivotX(0);
        toView.setPivotY(0);
        toView.setScaleX(moveData.widthScale);
        toView.setScaleY(moveData.heightScale);
        toView.setTranslationX(moveData.leftDelta);
        toView.setTranslationY(moveData.topDelta);

        toView.animate().setDuration(moveData.duration).
                scaleX(1).scaleY(1).
                translationX(0).translationY(0).
                setInterpolator(interpolator);
    }

    @Override
    public void startExitAnimation(MoveData moveData, Runnable endAction) {
        final View view = moveData.toView;
        int duration = moveData.duration;
        int leftDelta = moveData.leftDelta;
        int topDelta = moveData.topDelta;
        float widthScale = moveData.widthScale;
        float heightScale = moveData.heightScale;
        view.animate().setDuration(duration).
                scaleX(widthScale).scaleY(heightScale).
                translationX(leftDelta).translationY(topDelta);
        view.postDelayed(endAction, duration + 1);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.clearAnimation();
                fromView.setVisibility(View.VISIBLE);
            }
        }, duration);
    }
}
