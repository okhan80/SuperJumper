package com.khangames.androidgame.superjumper;

import com.khangames.framework.Screen;
import com.khangames.framework.com.khangames.framework.impl.GLGame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 2/1/12
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuperJumper extends GLGame {
    boolean firstTimeCreate = true;
    
    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        super.onSurfaceCreated(gl10, eglConfig);
        if(firstTimeCreate) {
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        } else {
            Assets.reload();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Settings.soundEnabled) {
            Assets.music.pause();
        }
    }
}
