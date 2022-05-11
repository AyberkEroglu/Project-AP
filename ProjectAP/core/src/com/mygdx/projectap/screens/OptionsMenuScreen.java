package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.ProjectAP;

public class OptionsMenuScreen implements Screen {

    ProjectAP game;

    Texture optionsMenu;
    Texture backMenuButton;

    public OptionsMenuScreen(ProjectAP game) {
        this.game = game;

        optionsMenu = new Texture("menu assets/Options Menu/Options Menu.drawio.png");
        backMenuButton = new Texture("menu assets/Options Menu/Back Menu Button.drawio.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(optionsMenu, 0, 0, ProjectAP.WIDTH, ProjectAP.HEIGHT);

        game.batch.draw(backMenuButton, 30, ProjectAP.HEIGHT - 100, 150, 70);
        if (Gdx.input.getY() >= 30 && Gdx.input.getY() <= 100 && Gdx.input.getX() >= 30 && Gdx.input.getX() <= 180) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
