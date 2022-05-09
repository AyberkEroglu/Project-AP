package com.mygdx.projectap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ProjectAP extends Game {

    public static ProjectAP INSTANCE;
    public int widthScreen, heightScreen;
    private OrthographicCamera orthographicCamera;

    public ProjectAP() {
        INSTANCE = this;
    }

    @Override
    public void create() {
        this.widthScreen = Gdx.graphics.getWidth();
        this.heightScreen = Gdx.graphics.getHeight();
        this.orthographicCamera = new OrthographicCamera();
        this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
        setScreen(new GameScreen(orthographicCamera));
    }

}
