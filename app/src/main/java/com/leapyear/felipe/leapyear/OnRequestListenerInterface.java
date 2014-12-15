package com.leapyear.felipe.leapyear;

import android.util.Pair;
/**
 * Created by Felipe on 09/12/2014.
 */
public interface OnRequestListenerInterface {
    public void onRequestFinished(Pair<Integer, Boolean> result);
}
