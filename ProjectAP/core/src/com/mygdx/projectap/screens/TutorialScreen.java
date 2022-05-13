package com.mygdx.projectap.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mygdx.projectap.ProjectAP;

public class TutorialScreen extends GameScreen implements ContactListener {

    public TutorialScreen(OrthographicCamera camera, ProjectAP game, int levelNum) {
        super(camera, game, levelNum);
    }
}
