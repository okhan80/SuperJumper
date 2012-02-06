package com.khangames.framework.com.khangames.framework.impl;

import android.view.MotionEvent;
import android.view.View;
import com.khangames.framework.Input;
import com.khangames.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/17/12
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleTouchHandler implements TouchHandler {
    boolean isTouched;
    int touchX;
    int touchY;
    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<Input.TouchEvent>();
    List<Input.TouchEvent> touchEventsBuffer = new ArrayList<Input.TouchEvent>();
    float scaleX;
    float scaleY;
    
    public SingleTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        
        touchEventPool = new Pool<Input.TouchEvent>(factory, 100);
        view.setOnTouchListener(this);
        
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            if(pointer == 0) {
                return isTouched;
            } else {
                return false;
            }
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            return touchY;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                touchEventPool.free(touchEvents.get(i));
            }

            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this) {
            Input.TouchEvent touchEvent = touchEventPool.newObject();
            switch(motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }
            
            touchEvent.x = touchX = (int)(motionEvent.getX() * scaleX);
            touchEvent.y = touchY = (int)(motionEvent.getY() * scaleY);
            touchEventsBuffer.add(touchEvent);
            
            return true;
        }
    }
}
