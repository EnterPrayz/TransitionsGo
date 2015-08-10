package com.onebit.enterprayz.transitiongolib.view;

import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by urec on 02.08.15.
 */
public class ViewTransition {
    View from;
    View to;
    private int duration = 300;
    private TimeInterpolator interpolator;

    public ViewTransition(View from, View to, int duration, TimeInterpolator interpolator) {
        this.from = from;
        this.to = to;
        this.duration = duration;
        this.interpolator = interpolator;
    }

    public int getDuration() {
        return duration;
    }

    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    public static Builder with(View from, View to) {
        return Builder.build(from, to);
    }

    public static class Builder {

        private View from;
        private View to;
        private int duration = 300;
        private TimeInterpolator interpolator;
        private float[] aplha;

        private static Builder build(View from, View to) {
            return new Builder(from, to);
        }


        public Builder(View from, View to) {
            this.from = from;
            this.to = to;
        }

        public Builder withDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder withInterpolator(TimeInterpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public ViewTransition create() {
            return new ViewTransition(from, to, duration, interpolator);
        }
    }

}
