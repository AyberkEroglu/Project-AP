package com.mygdx.projectap.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.projectap.ProjectAP;

public class InfinityModeScreen extends GameScreen implements ContactListener {

    public InfinityModeScreen(OrthographicCamera camera, ProjectAP game, int levelNum) {
        super(camera, game, levelNum);
    }
}
