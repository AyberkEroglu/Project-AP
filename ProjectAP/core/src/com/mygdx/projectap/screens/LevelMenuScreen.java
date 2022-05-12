package com.mygdx.projectap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.projectap.ProjectAP;

public class LevelMenuScreen implements Screen {

    ProjectAP game;
    Texture levelMenu;
    Texture backButton;
    Texture levelPlayButton1;
    Texture levelPlayButton2;
    Texture levelPlayButton3;
    Texture levelPlayButton4;
    Texture levelPlayButton5;
    Texture levelPlayButton6;
    Texture levelPlayButton7;
    Texture levelPlayButton8;
    Texture levelPlayButton9;

    public LevelMenuScreen(ProjectAP game) {
        this.game = game;

        levelMenu = new Texture("menu assets/Level Menu/Level Menu.drawio.png");
        backButton = new Texture("menu assets/Level Menu/Back Button.drawio.png");
        levelPlayButton1 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton2 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton3 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton4 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton6 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton5 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton7 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton8 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");
        levelPlayButton9 = new Texture("menu assets/Level Menu/Play Level Button.drawio.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(levelMenu, 0, 0, ProjectAP.WIDTH, ProjectAP.HEIGHT);
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
                game.setScreen(new GameScreen(orthographicCamera, game, 1));
            }
        }
        game.batch.draw(levelPlayButton2, 770, ProjectAP.HEIGHT - 165, 60, 80);
        if (Gdx.input.getY() >= 85 && Gdx.input.getY() <= 165 && Gdx.input.getX() >= 770 && Gdx.input.getX() <= 830) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 2));
            }
        }
        game.batch.draw(levelPlayButton3, 1160, ProjectAP.HEIGHT - 165, 60, 80);
        if (Gdx.input.getY() >= 85 && Gdx.input.getY() <= 165 && Gdx.input.getX() >= 1160 && Gdx.input.getX() <= 1220) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 3));
            }
        }
        game.batch.draw(levelPlayButton4, 380, ProjectAP.HEIGHT - 445, 60, 80);
        if (Gdx.input.getY() >= 365 && Gdx.input.getY() <= 445 && Gdx.input.getX() >= 380 && Gdx.input.getX() <= 440) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 4));
            }
        }
        game.batch.draw(levelPlayButton5, 770, ProjectAP.HEIGHT - 445, 60, 80);
        if (Gdx.input.getY() >= 365 && Gdx.input.getY() <= 445 && Gdx.input.getX() >= 770 && Gdx.input.getX() <= 830) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 5));
            }
        }
        game.batch.draw(levelPlayButton6, 1160, ProjectAP.HEIGHT - 445, 60, 80);
        if (Gdx.input.getY() >= 365 && Gdx.input.getY() <= 445 && Gdx.input.getX() >= 1160 && Gdx.input.getX() <= 1220) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 6));
            }
        }
        game.batch.draw(levelPlayButton7, 380, ProjectAP.HEIGHT - 725, 60, 80);
        if (Gdx.input.getY() >= 645 && Gdx.input.getY() <= 725 && Gdx.input.getX() >= 380 && Gdx.input.getX() <= 440) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 7));
            }
        }
        game.batch.draw(levelPlayButton8, 770, ProjectAP.HEIGHT - 725, 60, 80);
        if (Gdx.input.getY() >= 645 && Gdx.input.getY() <= 725 && Gdx.input.getX() >= 770 && Gdx.input.getX() <= 830) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 8));
            }
        }

        game.batch.draw(levelPlayButton9, 1160, ProjectAP.HEIGHT - 725, 60, 80);
        if (Gdx.input.getY() >= 645 && Gdx.input.getY() <= 725 && Gdx.input.getX() >= 1160 && Gdx.input.getX() <= 1220) {
            if (Gdx.input.justTouched()) {
                this.dispose();
                OrthographicCamera orthographicCamera = new OrthographicCamera();
                orthographicCamera.setToOrtho(false, ProjectAP.WIDTH, ProjectAP.HEIGHT);
                game.setScreen(new GameScreen(orthographicCamera, game, 9));
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
