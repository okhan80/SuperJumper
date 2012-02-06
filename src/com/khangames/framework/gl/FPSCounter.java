package com.khangames.framework.gl;

import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/24/12
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class FPSCounter {
    long startTime = System.nanoTime();
    int frames = 0;
    
    public void logFrame() {
        frames++;
        if(System.nanoTime() - startTime >= 1000000000) {
            Log.d("FPSCounter", "fps: " + frames);
            frames = 0;
            startTime = System.nanoTime();
        }
    }
}
