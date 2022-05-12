package com.mygdx.projectap.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Levels implements Screen {

    int levelNo;
    TiledMap levelMap;

    public Levels(int level) {
        this.levelNo = level;
        this.levelMap = new TmxMapLoader().load("maps/map" + levelNo + "/map" + levelNo + ".tmx");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
