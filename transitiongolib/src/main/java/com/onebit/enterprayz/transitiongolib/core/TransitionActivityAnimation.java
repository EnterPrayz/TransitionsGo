package com.onebit.enterprayz.transitiongolib.core;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.onebit.enterprayz.transitiongolib.abstractions.TransitionAnimation;

import java.util.Hashtable;

/**
 * Created by takam on 2015/05/17.
 */
public class TransitionActivityAnimation extends TransitionAnimation {
    @Override
    public MoveData startEnterAnimation(Context context, String transitionName, final View toView, Bundle transitionBundle, Bundle savedInstanceState, int duration, final TimeInterpolator interpolator) {
        final TransitionData transitionData = new TransitionData(context, transitionBundle, transitionName);
        trySetImageToView(toView, transitionName);
        trySetTextToView(toView, transitionName);
        final MoveData moveData = new MoveData();
        moveData.toView = toView;
        moveData.duration = duration;
        if (savedInstanceState == null) {
            ViewTreeObserver observer = toView.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    toView.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[] screenLocation = new int[2];
                    toView.getLocationOnScreen(screenLocation);
                    moveData.leftDelta = transitionData.thumbnailLeft - screenLocation[0];
                    moveData.topDelta = transitionData.thumbnailTop - screenLocation[1];

                    moveData.widthScale = (float) transitionData.thumbnailWidth / toView.getWidth();
                    moveData.heightScale = (float) transitionData.thumbnailHeight / toView.getHeight();

                    runActivityEnterAnimation(moveData, interpolator);

                    return true;
                }
            });
        }
        return moveData;
    }

    @Override
    public void startExitAnimation(MoveData moveData, final Runnable endAction) {
        View view = moveData.toView;
        int duration = moveData.duration;
        int leftDelta = moveData.leftDelta;
        int topDelta = moveData.topDelta;
        float widthScale = moveData.widthScale;
        float heightScale = moveData.heightScale;
        view.animate().setDuration(duration).
                scaleX(widthScale).scaleY(heightScale).
                translationX(leftDelta).translationY(topDelta);
        view.postDelayed(endAction, duration);
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

}
