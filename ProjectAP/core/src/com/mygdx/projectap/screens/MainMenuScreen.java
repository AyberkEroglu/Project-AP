package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.ProjectAP;

public class MainMenuScreen implements Screen {

    ProjectAP game;

    Texture mainMenu;
    Texture playButton;
    Texture exitButton;
    Texture optionsButton;

    public MainMenuScreen(ProjectAP game) {
        this.game = game;

        mainMenu = new Texture("menu assets/Main Menu/Main Menu.drawio.png");
        playButton = new Texture("menu assets/Main Menu/Play Button.drawio.png");
        optionsButton = new Texture("menu assets/Main Menu/Options Button.drawio.png");
        exitButton = new Texture("menu assets/Main Menu/Exit Button.drawio.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(mainMenu, 0, 0, ProjectAP.WIDTH, ProjectAP.HEIGHT);

        game.batch.draw(playButton, ProjectAP.WIDTH / 2 - playButton.getWidth() / 2, 580, 250, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 680 && Gdx.input.getY() <= ProjectAP.HEIGHT - 580 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - playButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - playButton.getWidth() / 2 + 250) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new PlayMenuScreen(game));
            }
        }

        game.batch.draw(optionsButton, ProjectAP.WIDTH / 2 - optionsButton.getWidth() / 2, 420, 400, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 520 && Gdx.input.getY() <= ProjectAP.HEIGHT - 420 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - optionsButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - optionsButton.getWidth() / 2 + 400) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new OptionsMenuScreen(game));
            }
        }

        game.batch.draw(exitButton, ProjectAP.WIDTH / 2 - exitButton.getWidth() / 2, 260, 200, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 360 && Gdx.input.getY() <= ProjectAP.HEIGHT - 260 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - exitButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - exitButton.getWidth() / 2 + 200) {
            if (Gdx.input.justTouched()) {
                Gdx.app.exit();
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
