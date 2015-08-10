package com.onebit.enterprayz.transitiongolib.core;

import android.graphics.Bitmap;

import java.util.Hashtable;

/**
 * Created by urec on 10.08.15.
 */
public class Cash {
    private static Hashtable<String, Bitmap> imageCash;
    private static Hashtable<String, String> textCash;

    public static Hashtable<String, Bitmap> getImageCash() {
        if (imageCash == null) {
            imageCash = new Hashtable<>();
        }
        return imageCash;
    }

    public static Hashtable<String, String> getTextCash() {
        if (textCash == null) {
            textCash = new Hashtable<>();
        }
        return textCash;
    }

    public static void add(String transitionName, Bitmap data) {
        getImageCash().put(transitionName, data);
    }

    public static void add(String transitionName, String data) {
        getTextCash().put(transitionName, data);
    }
}
