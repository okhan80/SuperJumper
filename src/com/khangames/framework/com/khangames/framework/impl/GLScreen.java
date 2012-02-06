package com.khangames.framework.com.khangames.framework.impl;

import com.khangames.framework.Game;
import com.khangames.framework.Screen;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 2/1/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame)game;
        glGraphics = ((GLGame)game).getGlGraphics();
    }
}
