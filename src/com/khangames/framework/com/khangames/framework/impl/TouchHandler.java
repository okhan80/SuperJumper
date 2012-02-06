package com.khangames.framework.com.khangames.framework.impl;

import android.view.View;
import com.khangames.framework.Input;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/17/12
 * Time: 9:15 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);

    public List<Input.TouchEvent> getTouchEvents();
}
