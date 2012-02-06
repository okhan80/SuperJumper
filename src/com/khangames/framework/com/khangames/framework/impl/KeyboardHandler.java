package com.khangames.framework.com.khangames.framework.impl;

import com.khangames.framework.Input.KeyEvent;
import android.view.View;
import com.khangames.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/13/12
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeyboardHandler implements View.OnKeyListener {
    
    boolean[] pressedKeys = new boolean[128];
    Pool<KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
    List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

    public KeyboardHandler(View view) {
        Pool.PoolObjectFactory<KeyEvent> factory = new Pool.PoolObjectFactory<KeyEvent>() {
            @Override
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
        
        keyEventPool = new Pool<KeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }
    
    @Override
    public boolean onKey(View view, int keyCode, android.view.KeyEvent event) {
        if(event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE) {
            return false;
        }
        
        synchronized (this) {
            KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            
            if(event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                keyEvent.type = KeyEvent.KEY_DOWN;
                if(keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = true;
                }
            }
            if(event.getAction() == android.view.KeyEvent.ACTION_UP) {
                keyEvent.type = KeyEvent.KEY_UP;
                if(keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = false;
                }
            }
            
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }
    
    public boolean isKeyPressed(int keyCode) {
        return !(keyCode < 0 || keyCode > 127) && pressedKeys[keyCode];
    }
    
    public List<KeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = keyEvents.size();
            for(int i = 0; i < len; i++) {
                keyEventPool.free(keyEvents.get(i));

            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
}
