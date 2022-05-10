package com.mygdx.projectap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.projectap.screens.MainMenuScreen;

public class ProjectAP extends Game {

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
