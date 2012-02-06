package com.khangames.framework.math;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/30/12
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Circle {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius) {
        this.center.set(x, y);
        this.radius = radius;
    }


}
