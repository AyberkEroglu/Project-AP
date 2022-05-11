package com.mygdx.projectap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.projectap.screens.MainMenuScreen;

public class ProjectAP extends Game {

    public static final float SLOWED_TIME_SCALE = 0.25f;
    public static final float FAST_TIME_SCALE = 1f;
    public static final float BULLET_SPEED = 20f;
    public static final float ENEMY_SPEED = 10f;
    public static final float GRAVITY = -9.81f;
    public float timeScale;

    public static int WIDTH = 1600;
    public static int HEIGHT = 900;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
