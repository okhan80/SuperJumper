package com.khangames.androidgame.superjumper;

import com.khangames.framework.object.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 2/1/12
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Castle extends GameObject {
    public static float CASTLE_WIDTH = 1.7f;
    public static float CASTLE_HEIGHT = 1.7f;

    public Castle(float x, float y) {
        super(x, y, CASTLE_WIDTH, CASTLE_HEIGHT);
    }
}
