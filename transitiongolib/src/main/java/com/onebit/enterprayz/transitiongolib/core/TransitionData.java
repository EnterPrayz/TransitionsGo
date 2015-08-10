package com.onebit.enterprayz.transitiongolib.core;

import android.content.Context;
import android.os.Bundle;

import com.onebit.enterprayz.transitiongolib.BuildConfigUtils;


/**
 * Created by takam on 2015/05/16.
 */
public class TransitionData {
    public static final String EXTRA_IMAGE_LEFT = ".left";
    public static final String EXTRA_IMAGE_TOP = ".top";
    public static final String EXTRA_IMAGE_WIDTH = ".width";
    public static final String EXTRA_IMAGE_HEIGHT = ".height";
    public static final String EXTRA_TRANSITION_NAME = ".name";

    public final int thumbnailTop;
    public final int thumbnailLeft;
    public final int thumbnailWidth;
    public final int thumbnailHeight;
    private String appId;

    private Bundle bundle;
    private String transitionName;

    public TransitionData(Context context, Bundle bundle, String transitionName, int thumbnailLeft, int thumbnailTop, int thumbnailWidth, int thumbnailHeight) {
        setAppId(context);
        this.bundle = bundle;
        this.transitionName = transitionName;
        this.thumbnailLeft = thumbnailLeft;
        this.thumbnailTop = thumbnailTop;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public TransitionData(Context context, Bundle bundle, String transitionName) {
        setAppId(context);
        thumbnailTop = bundle.getInt(transitionName + appId + EXTRA_IMAGE_TOP);
        thumbnailLeft = bundle.getInt(transitionName + appId + EXTRA_IMAGE_LEFT);
        thumbnailWidth = bundle.getInt(transitionName + appId + EXTRA_IMAGE_WIDTH);
        thumbnailHeight = bundle.getInt(transitionName + appId + EXTRA_IMAGE_HEIGHT);
    }

    private void setAppId(Context context) {
        appId = (String) BuildConfigUtils.getBuildConfigValue(context, "APPLICATION_ID");
    }


    public void makeBundle() {
        bundle.putInt(transitionName + appId + EXTRA_IMAGE_LEFT, thumbnailLeft);
        bundle.putInt(transitionName + appId + EXTRA_IMAGE_TOP, thumbnailTop);
        bundle.putInt(transitionName + appId + EXTRA_IMAGE_WIDTH, thumbnailWidth);
        bundle.putInt(transitionName + appId + EXTRA_IMAGE_HEIGHT, thumbnailHeight);
        bundle.putString(EXTRA_TRANSITION_NAME, transitionName);
    }
}
