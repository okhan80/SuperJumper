package com.khangames.framework;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/9/12
 * Time: 2:28 PM
 * How the user is able to interact with the game through keyboard or touch
 */
public interface Input {
    public static class KeyEvent {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;
        
        public int type;
        public int keyCode;
        public char keyChar;
        
    }
    
    public static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        
        public int type;
        public int x, y;
        public int pointer;
    }

    /**
     * Checks to see if the key is pressed
     * @param keyCode - the pressed key's keycode
     * @return - boolean
     */
    public boolean isKeyPressed(int keyCode);

    /**
     * Checks to see if there is a touch on the screen
     * @param pointer - First touch is assigned 0, and if multi-touch is supported this will designate the other touches
     * @return - boolean
     */
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public float getAccelX();
    
    public float getAccelY();
    
    public float getAccelZ();

    /**
     * Used for Event-based handling where all previous input is recorded and retained
     * @return - List<KeyEvent>
     */
    public List<KeyEvent> getKeyEvents();

    /**
     * Used for Event-based handling where all previous touches are recorded and retained
     * @return - List<TouchEvent>
     */
    public List<TouchEvent> getTouchEvents();

}
