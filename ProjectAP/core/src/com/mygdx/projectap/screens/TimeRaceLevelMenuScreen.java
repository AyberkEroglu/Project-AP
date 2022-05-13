package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.ProjectAP;

public class TimeRaceLevelMenuScreen extends LevelMenuScreen {

    private Texture timeRaceMenu;

    public TimeRaceLevelMenuScreen(ProjectAP game) {
        super(game);

        timeRaceMenu = new Texture("menu assets/TimeRace Menu/Time Race Menu.drawio.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(timeRaceMenu, 0, 0, 1600, 900);
        game.batch.draw(backButton, 30, ProjectAP.HEIGHT - 100, 150, 70);
        if (Gdx.input.getY() >= 30 && Gdx.input.getY() <= 100 && Gdx.input.getX() >= 30 && Gdx.input.getX() <= 180) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new PlayMenuScreen(game));
            }
        }
        game.batch.draw(levelPlayButton1, 380, ProjectAP.HEIGHT - 165, 60, 80);
        if (Gdx.input.getY() >= 85 && Gdx.input.getY() <= 165 && Gdx.input.getX() >= 380 && Gdx.input.getX() <= 440) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 1, 15));
            }
        }
        game.batch.draw(levelPlayButton2, 770, ProjectAP.HEIGHT - 165, 60, 80);
        if (Gdx.input.getY() >= 85 && Gdx.input.getY() <= 165 && Gdx.input.getX() >= 770 && Gdx.input.getX() <= 830) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 2, 15));
            }
        }
        game.batch.draw(levelPlayButton3, 1160, ProjectAP.HEIGHT - 165, 60, 80);
        if (Gdx.input.getY() >= 85 && Gdx.input.getY() <= 165 && Gdx.input.getX() >= 1160 && Gdx.input.getX() <= 1220) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 3, 15));
            }
        }
        game.batch.draw(levelPlayButton4, 380, ProjectAP.HEIGHT - 445, 60, 80);
        if (Gdx.input.getY() >= 365 && Gdx.input.getY() <= 445 && Gdx.input.getX() >= 380 && Gdx.input.getX() <= 440) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 4, 15));
            }
        }
        game.batch.draw(levelPlayButton5, 770, ProjectAP.HEIGHT - 445, 60, 80);
        if (Gdx.input.getY() >= 365 && Gdx.input.getY() <= 445 && Gdx.input.getX() >= 770 && Gdx.input.getX() <= 830) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 5, 15));
            }
        }
        game.batch.draw(levelPlayButton6, 1160, ProjectAP.HEIGHT - 445, 60, 80);
        if (Gdx.input.getY() >= 365 && Gdx.input.getY() <= 445 && Gdx.input.getX() >= 1160 && Gdx.input.getX() <= 1220) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 6, 15));
            }
        }
        game.batch.draw(levelPlayButton7, 380, ProjectAP.HEIGHT - 725, 60, 80);
        if (Gdx.input.getY() >= 645 && Gdx.input.getY() <= 725 && Gdx.input.getX() >= 380 && Gdx.input.getX() <= 440) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 7, 15));
            }
        }
        game.batch.draw(levelPlayButton8, 770, ProjectAP.HEIGHT - 725, 60, 80);
        if (Gdx.input.getY() >= 645 && Gdx.input.getY() <= 725 && Gdx.input.getX() >= 770 && Gdx.input.getX() <= 830) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 8, 15));
            }
        }

        game.batch.draw(levelPlayButton9, 1160, ProjectAP.HEIGHT - 725, 60, 80);
        if (Gdx.input.getY() >= 645 && Gdx.input.getY() <= 725 && Gdx.input.getX() >= 1160 && Gdx.input.getX() <= 1220) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new TimeRaceScreen(orthographicCamera, game, 9, 15));
            }
        }
        game.batch.end();
    }
}
