package com.khangames.framework;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/10/12
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Game {
    
    public Input getInput();
    
    public FileIO getFileIO();
    
    public Graphics getGraphics();
    
    public Audio getAudio();
    
    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
    
}
