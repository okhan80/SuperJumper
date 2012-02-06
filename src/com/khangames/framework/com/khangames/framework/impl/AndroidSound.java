package com.khangames.framework.com.khangames.framework.impl;

import android.media.SoundPool;
import com.khangames.framework.Sound;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/13/12
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
