package com.khangames.framework;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/9/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Music {
    public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();

}
