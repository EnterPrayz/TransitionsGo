package com.onebit.enterprayz.transitiongolib.abstractions;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.onebit.enterprayz.transitiongolib.core.Cash;
import com.onebit.enterprayz.transitiongolib.core.MoveData;

/**
 * Created by urec on 10.08.15.
 */
public abstract class TransitionAnimation {

    public MoveData startEnterAnimation() {
        throw new UnsupportedOperationException("Invalid operation. You must override method");
    }

    public abstract void startExitAnimation(MoveData moveData, final Runnable endAction);

    public void trySetTextToView(View toView, String transitionName) {
        if (Cash.getTextCash().containsKey(transitionName)) {
            String text = Cash.getTextCash().get(transitionName);
            if (toView instanceof TextView) {
                ((TextView) toView).setText(text);
            }
        }
    }

    public void trySetImageToView(View toView, String transitionName) {
        if (Cash.getImageCash().containsKey(transitionName)) {
            Bitmap bitmap = Cash.getImageCash().get(transitionName);
            if (toView instanceof ImageView) {
                final ImageView toImageView = (ImageView) toView;
                toImageView.setImageBitmap(bitmap);
            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    toView.setBackground(new BitmapDrawable(toView.getResources(), bitmap));
                } else {
                    toView.setBackgroundDrawable(new BitmapDrawable(toView.getResources(), bitmap));
                }
            }
        }
    }
}
