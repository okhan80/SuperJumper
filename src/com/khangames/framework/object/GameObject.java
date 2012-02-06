package com.khangames.framework.object;

import com.khangames.framework.math.Rectangle;
import com.khangames.framework.math.Vector2;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/31/12
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;
    
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }
}
