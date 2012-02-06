package com.khangames.framework.math;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/30/12
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rectangle {
    public final Vector2 lowerLeft;
    public float width, height;
    
    public Rectangle(float x, float y, float width, float height) {
        this.lowerLeft = new Vector2(x, y);
        this.width = width;
        this.height = height;
        
    }

    
}
