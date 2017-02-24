package com.maxst.revelio;

import android.util.Log;

/**
 * Created by Charles on 11/24/16.
 */
public class NativeManager {

    static {
        System.loadLibrary("revelio");
    }

    public static native int setViewMode(int type);
}
