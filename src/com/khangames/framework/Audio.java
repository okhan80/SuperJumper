package com.khangames.framework;


/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/9/12
 * Time: 2:43 PM
 * TCreate new Music and Sound instances.
 * Sound instance represents a short sound effect kept entirely in memory
 * Music Instance represents a streamed audio file
 */
public interface Audio {
    public Music newMusic(String fileName);
    public Sound newSound(String fileName);
}
