package com.khangames.framework.object;

import com.khangames.framework.math.Vector2;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/31/12
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicGameObject extends GameObject {
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
