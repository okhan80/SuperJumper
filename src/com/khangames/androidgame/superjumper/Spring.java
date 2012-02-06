package com.khangames.androidgame.superjumper;

import com.khangames.framework.object.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 2/1/12
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Spring extends GameObject {
    public static float SPRING_WIDTH = 0.3f;
    public static float SPRING_HEIGHT = 0.3f;

    public Spring(float x, float y) {
        super(x, y, SPRING_WIDTH, SPRING_HEIGHT);
    }
}
