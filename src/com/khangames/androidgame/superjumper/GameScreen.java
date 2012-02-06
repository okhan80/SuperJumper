package com.khangames.androidgame.superjumper;


import com.khangames.framework.Game;
import com.khangames.framework.Input;
import com.khangames.framework.com.khangames.framework.impl.GLScreen;
import com.khangames.framework.gl.Camera2D;
import com.khangames.framework.gl.SpriteBatcher;
import com.khangames.framework.math.OverlapTester;
import com.khangames.framework.math.Rectangle;
import com.khangames.framework.math.Vector2;

import javax.microedition.khronos.opengles.GL10;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 2/3/12
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen extends GLScreen {
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;
    
    int state;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;
    World world;
    World.WorldListener worldListener;
    WorldRenderer renderer;
    Rectangle pauseBounds;
    Rectangle resumeBounds;
    Rectangle quitBounds;
    int lastScore;
    String scoreString;
    
    public GameScreen(Game game) {
        super(game);
        state = GAME_READY;
        guiCam = new Camera2D(glGraphics, 320, 480);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        worldListener = new World.WorldListener() {
            @Override
            public void jump() {
                Assets.playSound(Assets.jumpSound);
            }

            @Override
            public void highJump() {
                Assets.playSound(Assets.highJumpSound);
            }

            @Override
            public void hit() {
                Assets.playSound(Assets.hitSound);
            }

            @Override
            public void coin() {
                Assets.playSound(Assets.coinSound);
            }
        };
        
        world = new World(worldListener);
        renderer = new WorldRenderer(glGraphics, batcher, world);
        pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
        resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
        quitBounds = new Rectangle(1160 - 96, 240 - 36, 192, 36);
        lastScore = 0;
        scoreString = "score: 0";
    }

    @Override
    public void update(float deltaTime) {
        if(deltaTime > 0.1f) {
            deltaTime = 0.1f;
        }
        
        switch (state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_PAUSED:
                updatePaused();
                break;
            case GAME_LEVEL_END:
                updateLevelEnd();
                break;
            case GAME_OVER:
                updateGameOver();
                break;
        }
    }

    private void updateGameOver() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP) {
                continue;
            }
            game.setScreen(new MainMenuScreen(game));
        }
    }

    private void updateLevelEnd() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP) {
                continue;
            }
            world = new World(worldListener);
            renderer = new WorldRenderer(glGraphics, batcher, world);
            world.score = lastScore;
            state = GAME_READY;
        }
    }

    private void updatePaused() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP) {
                continue;
            }

            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

            if(OverlapTester.pointInRectangle(resumeBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_RUNNING;
                return;
            }

            if(OverlapTester.pointInRectangle(quitBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
                return;
            }
        }
    }

    private void updateRunning(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_UP) {
                continue;
            }
            
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
            
            if(OverlapTester.pointInRectangle(pauseBounds, touchPoint)) {
                Assets.playSound(Assets.clickSound);
                state = GAME_PAUSED;
                return;
            }
        }
        
        world.update(deltaTime, game.getInput().getAccelX());
        if(world.score != lastScore) {
            lastScore = world.score;
            scoreString = "" + lastScore;
        }
        
        if(world.state == World.WORLD_STATE_NEXT_LEVEL) {
            state = GAME_LEVEL_END;
        }
        
        if(world.state == World.WORLD_STATE_GAME_OVER) {
            state = GAME_OVER;
            if(lastScore >= Settings.highscores[4]) {
                scoreString = "new highscore: " + lastScore;

            } else {
                scoreString = "score: " + lastScore;
            }

            Settings.addScore(lastScore);
            Settings.save(game.getFileIO());
        }
    }

    private void updateReady() {
        if(game.getInput().getTouchEvents().size() > 0) {
            state = GAME_RUNNING;
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        renderer.render();
        
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.items);
        switch (state) {
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_LEVEL_END:
                presentLevelEnd();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }

    private void presentGameOver() {
        batcher.drawSprite(160, 240, 160, 96, Assets.gameOver);
        float scoreWidth = Assets.font.glyphWidth * scoreString.length();
        Assets.font.drawText(batcher, scoreString, 160 - scoreWidth / 2, 480 - 20);
    }

    private void presentLevelEnd() {
        String topText = "the princess is ...";
        String bottomText = "in another castle!";
        float topWidth = Assets.font.glyphWidth * topText.length();
        float bottomWidth = Assets.font.glyphWidth * bottomText.length();
        Assets.font.drawText(batcher, topText, 160 - topWidth / 2, 480 - 40);
        Assets.font.drawText(batcher, bottomText, 160 - bottomWidth / 2, 40);
        
    }

    private void presentPaused() {
        batcher.drawSprite(160, 240, 192, 96, Assets.pauseMenu);
        Assets.font.drawText(batcher, scoreString, 16, 480 - 20);
    }

    private void presentRunning() {
        batcher.drawSprite(320 - 32, 480 - 32, 64, 64, Assets.pause);
        Assets.font.drawText(batcher, scoreString, 16, 480 - 20);
        
    }

    private void presentReady() {
        batcher.drawSprite(160, 240, 192, 32, Assets.ready);    
    }

    @Override
    public void pause() {
        if(state == GAME_RUNNING) {
            state = GAME_PAUSED;
        }
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
