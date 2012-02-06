package com.khangames.framework.com.khangames.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import com.khangames.framework.Audio;
import com.khangames.framework.Music;
import com.khangames.framework.Sound;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/13/12
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;
    
    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        
    }

    @Override
    public Music newMusic(String fileName) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
            return new AndroidMusic(assetDescriptor);
        } catch(IOException ex) {
            throw new RuntimeException("Couldn't load music '" + fileName + "'");
        }
    }

    @Override
    public Sound newSound(String fileName) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't load sound '" + fileName + "'");
        }
    }
}
