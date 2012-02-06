package com.khangames.androidgame.superjumper;

import com.khangames.framework.object.DynamicGameObject;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 2/2/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Squirrel extends DynamicGameObject {
    public static final float SQUIRREL_WIDTH = 1.0f;
    public static final float SQUIRREL_HEIGHT = 0.6f;
    public static final float SQUIRREL_VELOCITY = 3.0f;

    float stateTime = 0;

    public Squirrel(float x, float y) {
        super(x, y, SQUIRREL_WIDTH, SQUIRREL_HEIGHT);
        velocity.set(SQUIRREL_VELOCITY, 0);
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y *deltaTime);
        bounds.lowerLeft.set(position).sub(SQUIRREL_WIDTH / 2, SQUIRREL_HEIGHT / 2);

        if(position.x < SQUIRREL_WIDTH / 2) {
            position.x = SQUIRREL_WIDTH / 2;
            velocity.x = SQUIRREL_VELOCITY;
        }

        if(position.x > World.WORLD_WIDTH - SQUIRREL_WIDTH / 2) {
            position.x = World.WORLD_WIDTH - SQUIRREL_WIDTH / 2;
            velocity.x = -SQUIRREL_VELOCITY;
        }

        stateTime += deltaTime;
    }
}
