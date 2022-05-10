package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.ProjectAP;

public class PlayMenuScreen implements Screen {

    ProjectAP game;

    Texture playMenu;
    Texture backMenuButton;
    Texture tutorialButton;
    Texture levelsButton;
    Texture timeRaceButton;
    Texture infinityModeButton;

    public PlayMenuScreen(ProjectAP game) {
        this.game = game;

        playMenu = new Texture("menu assets/Background.drawio.png");
        tutorialButton = new Texture("menu assets/Tutorial Button.drawio.png");
        levelsButton = new Texture("menu assets/Levels Menu Button.drawio.png");
        timeRaceButton = new Texture("menu assets/Time Race Button.drawio.png");
        infinityModeButton = new Texture("menu assets/Infinity Mode Button.drawio.png");
        backMenuButton = new Texture("menu assets/Back Menu Button.drawio.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(playMenu, 0, 0, ProjectAP.WIDTH, ProjectAP.HEIGHT);

        game.batch.draw(backMenuButton, 30, ProjectAP.HEIGHT - 100, 150, 70);
        if (Gdx.input.getY() >= 30 && Gdx.input.getY() <= 100 && Gdx.input.getX() >= 30 && Gdx.input.getX() <= 180) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }

        game.batch.draw(tutorialButton, ProjectAP.WIDTH / 2 - tutorialButton.getWidth() / 2, 740, 390, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 840 && Gdx.input.getY() <= ProjectAP.HEIGHT - 740 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - tutorialButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - tutorialButton.getWidth() / 2 + 390) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        }

        game.batch.draw(levelsButton, ProjectAP.WIDTH / 2 - levelsButton.getWidth() / 2, 580, 290, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 680 && Gdx.input.getY() <= ProjectAP.HEIGHT - 580 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - levelsButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - levelsButton.getWidth() / 2 + 290) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new LevelMenuScreen(game));
            }
        }

        game.batch.draw(timeRaceButton, ProjectAP.WIDTH / 2 - timeRaceButton.getWidth() / 2, 420, 420, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 520 && Gdx.input.getY() <= ProjectAP.HEIGHT - 420 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - timeRaceButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - timeRaceButton.getWidth() / 2 + 420) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        }

        game.batch.draw(infinityModeButton, ProjectAP.WIDTH / 2 - infinityModeButton.getWidth() / 2, 260, 580, 100);
        if (Gdx.input.getY() >= ProjectAP.HEIGHT - 360 && Gdx.input.getY() <= ProjectAP.HEIGHT - 260 && Gdx.input.getX() >= ProjectAP.WIDTH / 2 - infinityModeButton.getWidth() / 2 && Gdx.input.getX() <= ProjectAP.WIDTH / 2 - infinityModeButton.getWidth() / 2 + 580) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
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
